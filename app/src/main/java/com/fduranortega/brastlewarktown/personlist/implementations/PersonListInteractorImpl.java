package com.fduranortega.brastlewarktown.personlist.implementations;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Filter;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListCallback;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.realm.ColorDB;
import com.fduranortega.brastlewarktown.realm.PersonDB;
import com.fduranortega.brastlewarktown.realm.ProfessionDB;
import com.fduranortega.brastlewarktown.realm.mappers.PersonDBMapper;
import com.fduranortega.brastlewarktown.rest.dto.DTOTown;
import com.fduranortega.brastlewarktown.rest.dto.mappers.DTOPersonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class PersonListInteractorImpl implements PersonListInteractor {


    public PersonListInteractorImpl() {

    }


    @Override
    public void getData(Filter filter, final PersonListCallback callback) {
        //Si tenemos internet
        if (App.INSTANCE.isNetworkAvailable()) {
            Log.d("Data source:", "From network");
            if (filter != null) {
                callback.displayFilterActionBar();
                getFromBD(filter, callback);
            } else {
                callback.showFilterButton();
                getFromService(callback);
            }
        }
        //Si no tenemos internet pero tenemos datos en la bd
        else if (!App.getRealm().isEmpty()) {
            Log.d("Data source:", "From database");
            getFromBD(null, callback);
        }
        //Si no tenemos internet ni tenemos datos en la bd
        else {
            Log.d("Data source:", "From file");
            getFromFile(callback);
        }
    }

    private void getFromBD(final Filter filter, final PersonListCallback callback) {


        final List<Person> lstPerson = new ArrayList<>();

        App.getRealm().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmQuery<PersonDB> query = bgRealm.where(PersonDB.class);

                if (filter != null) {
                    if (filter.getName() != null) {
                        query.like("name", "*" + filter.getName() + "*", Case.INSENSITIVE);
                    }

                    if (filter.getMinAge() != null) {
                        query.greaterThanOrEqualTo("age", filter.getMinAge());
                    }

                    if (filter.getMinWeight() != null) {
                        query.greaterThanOrEqualTo("weight", filter.getMinWeight());
                    }

                    if (filter.getMinHeight() != null) {
                        query.greaterThanOrEqualTo("height", filter.getMinHeight());
                    }

                    if (filter.getMaxAge() != null) {
                        query.lessThanOrEqualTo("age", filter.getMaxAge());
                    }

                    if (filter.getMaxWeight() != null) {
                        query.lessThanOrEqualTo("weight", filter.getMaxWeight());
                    }

                    if (filter.getMaxHeight() != null) {
                        query.lessThanOrEqualTo("height", filter.getMaxHeight());
                    }

                    if (filter.getHairColor() != null) {
                        query.like("hairColor.color", filter.getHairColor());
                    }

                    if (filter.getProfession() != null) {
                        query.like("professions.profession", filter.getProfession());
                    }
                }



                RealmResults<PersonDB> filter = query.findAll();
                for (PersonDB personDB : filter) {
                    Person person = PersonDBMapper.convert(personDB, false);
                    lstPerson.add(person);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.dataResponse(lstPerson);
            }
        });


    }


    private void getFromService(final PersonListCallback callback) {
        callback.showLoading();
        App.getRestClient().getPersonService().getPersons(new Callback<DTOTown>() {
            @Override
            public void success(DTOTown dtoTown, Response response) {
                List<Person> lstPerson = DTOPersonMapper.convertList(dtoTown.getBrastlewark());
                callback.dataResponse(lstPerson);
                callback.hideLoading();
                setDataToBD(lstPerson);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.dataError(App.INSTANCE.getApplicationContext().getString(R.string.service_error));
                callback.hideLoading();
            }
        });
    }

    private void setDataToBD(final List<Person> lstPerson) {
        //Clean DB
        App.getRealm().beginTransaction();
        App.getRealm().deleteAll();
        App.getRealm().commitTransaction();

        App.getRealm().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {


                for (Person person : lstPerson) {
                    //Check if color is create to get it or create it
                    ColorDB personColor;
                    RealmResults<ColorDB> colors = bgRealm.where(ColorDB.class)
                            .equalTo("color", person.getHairColor()).findAll();
                    if (colors.size() > 0) {
                        personColor = colors.get(0);
                    } else {
                        personColor = new ColorDB();
                        personColor.setId(UUID.randomUUID().toString());
                        personColor.setColor(person.getHairColor());
                        personColor = bgRealm.copyToRealm(personColor);
                    }

                    //Check if job is create to get it or create it
                    RealmList<ProfessionDB> lstProfessions = new RealmList<>();
                    for (String profession : person.getProfessions()) {
                        ProfessionDB personProfession;
                        RealmResults<ProfessionDB> professions = bgRealm.where(ProfessionDB.class)
                                .equalTo("profession", profession).findAll();
                        if (professions.size() > 0) {
                            personProfession = professions.get(0);
                        } else {
                            personProfession = new ProfessionDB();
                            personProfession.setId(UUID.randomUUID().toString());
                            personProfession.setProfession(profession);
                            personProfession = bgRealm.copyToRealm(personProfession);
                        }
                        lstProfessions.add(personProfession);
                    }

                    PersonDB personDB = new PersonDB();

                    personDB.setId(person.getId());
                    personDB.setName(person.getName());
                    personDB.setPhoto(person.getPhoto());
                    Integer age = null;
                    if (person.getAge() != null) {
                        age = Integer.parseInt(person.getAge());
                    }
                    Double weight = null;
                    if (person.getWeight() != null) {
                        weight = Double.parseDouble(person.getWeight());
                    }
                    Double height = null;
                    if (person.getAge() != null) {
                        height = Double.parseDouble(person.getHeight());
                    }

                    personDB.setAge(age);
                    personDB.setWeight(weight);
                    personDB.setHeight(height);
                    //Fix because Realm doesnt support List<String>
                    String friendNames = "";
                    for (String friendName : person.getFriendsNames()) {
                        if (friendNames != "") {
                            friendNames += ",";
                        }
                        friendNames += friendName;
                    }
                    personDB.setFriendNames(friendNames);

                    personDB.setHairColor(personColor);
                    personDB.setProfessions(lstProfessions);

                    bgRealm.copyToRealm(personDB);
                }

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
//                Toast.makeText(App.INSTANCE.getApplicationContext(), "Realm Finish", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void getFromFile(final PersonListCallback callback) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        try {
            InputStream is = App.INSTANCE.getResources().openRawResource(R.raw.data);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            String jsonString = writer.toString();
            DTOTown dto = objectMapper.readValue(jsonString, DTOTown.class);
            List<Person> lstPerson = DTOPersonMapper.convertList(dto.getBrastlewark());
            setDataToBD(lstPerson);
            callback.dataResponse(lstPerson);
        } catch (IOException e) {
            callback.dataError(e.getMessage());
            e.printStackTrace();
        }
    }


}
