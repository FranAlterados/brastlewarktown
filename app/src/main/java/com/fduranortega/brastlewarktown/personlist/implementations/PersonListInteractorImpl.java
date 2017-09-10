package com.fduranortega.brastlewarktown.personlist.implementations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListCallback;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.realm.ColorDB;
import com.fduranortega.brastlewarktown.realm.PersonDB;
import com.fduranortega.brastlewarktown.realm.ProfessionDB;
import com.fduranortega.brastlewarktown.rest.dto.DTOTown;
import com.fduranortega.brastlewarktown.rest.dto.mappers.DTOPersonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
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
    public void getData(final PersonListCallback listener) {
        //Si tenemos internet
        getFromService(listener);
        //Si no tenemos internet pero tenemos datos en la bd
        //getFileFromBD(listener);
        //Si no tenemos internet ni tenemos datos en la bd
//        getFromFile(listener);
    }

    private void getFileFromBD(PersonListCallback listener) {
        //TODO
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
//                        bgRealm.beginTransaction();
                        personColor = new ColorDB();
                        personColor.setId(UUID.randomUUID().toString());
                        personColor.setColor(person.getHairColor());
                        personColor = bgRealm.copyToRealm(personColor);
//                        bgRealm.commitTransaction();
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
//                            bgRealm.beginTransaction();
                            personProfession = new ProfessionDB();
                            personProfession.setId(UUID.randomUUID().toString());
                            personProfession.setProfession(profession);
                            personProfession = bgRealm.copyToRealm(personProfession);
//                            bgRealm.commitTransaction();
                        }
                        lstProfessions.add(personProfession);
                    }
//                    bgRealm.beginTransaction();
                    PersonDB personDB = new PersonDB();

                    personDB.setId(person.getId());
                    personDB.setName(person.getName());
                    personDB.setPhoto(person.getPhoto());
                    personDB.setAge(person.getAge());
                    personDB.setWeight(person.getWeight());
                    personDB.setHeight(person.getHeight());
                    //Fix because Realm doesnt support List<String>
//                    String friendNames = "";
//                    for (String friendName : person.getFriends()) {
//                        if (friendNames != "") {
//                            friendNames += ",";
//                        }
//                        friendNames += friendName;
//                    }
//                    personDB.setFriendNames(friendNames);

                    personDB.setHairColor(personColor);
                    personDB.setProfessions(lstProfessions);

                    bgRealm.copyToRealm(personDB);
//                    bgRealm.commitTransaction();
                }

                //second round to make friend relationships
//                RealmResults<PersonDB> allPersonsDB = bgRealm.where(PersonDB.class).findAll();
//                for (PersonDB personDB : allPersonsDB) {
//                    List<String> lstNames = Arrays.asList(personDB.getFriendNames().split(","));
//                    for (String friendName : lstNames) {
//                        PersonDB friend = bgRealm.where(PersonDB.class).equalTo("name", friendName).findFirst();
//                        if (friend != null) {
//                            personDB.getFriends().add(friend);
//                        }
//                    }
//                }

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
            callback.dataResponse(lstPerson);
        } catch (IOException e) {
            callback.dataError(e.getMessage());
            e.printStackTrace();
        }
    }


}
