package com.qainfotech.tap.training.resourceio;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

    /**
     * get a list of individual objects from db json file
     *
     * @return
     */
    JSONObject jobj;
    List<Individual> ilist1;
    List<Individual> iList = new ArrayList<>();
    List<Team> ListTeam = new ArrayList<>();

    public TeamsJsonReader() {
        try {
            FileReader reader = new FileReader("C:\\Users\\piyusharora\\Documents\\NetBeansProjects\\assignment-resource-io-master\\src\\main\\resources\\db.json");
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject) parser.parse(reader);
            JSONArray jarray = (JSONArray) jobj.get("individuals");
            JSONObject jobjct[] = new JSONObject[jarray.size()];
            for (int i = 0; i < jarray.size(); i++) {
                jobjct[i] = (JSONObject) jarray.get(i);
                int id = ((Long) jobjct[i].get("id")).intValue();
                String name = jobjct[i].get("name").toString();
                Boolean active = (Boolean) jobjct[i].get("active");
                Map<String, Object> map = new HashMap();
                map.put("id", id);
                map.put("name", name);
                map.put("active", active);
                Individual ind = new Individual(map);
                iList.add(ind);

            }

            JSONArray jarray1 = (JSONArray) jobj.get("teams");
            JSONObject joa[] = new JSONObject[jarray1.size()];

            for (int i = 0; i < jarray1.size(); i++) {
                ilist1 = new ArrayList<>();
                joa[i] = (JSONObject) jarray1.get(i);
                Integer id = ((Long) joa[i].get("id")).intValue();
                String name = joa[i].get("name").toString();

                JSONArray jarray2 = (JSONArray) joa[i].get("members");
                for (int j = 0; j < jarray2.size(); j++) {
                    int idd = ((Long) jarray2.get(j)).intValue();
                    Individual ind = getIndividualById(idd);
                    ilist1.add(ind);

                }
                Map<String, Object> map = new HashMap();

                map.put("id", id);
                map.put("name", name);
                map.put("members", ilist1);

                Team tm = new Team(map);
                ListTeam.add(tm);

            }
        } catch (ObjectNotFoundException | IOException | ParseException e) {
            System.out.println(e);
        }
    }

    public List<Individual> getListOfIndividuals() {

        return iList;
    }

    /**
     * get individual object by id
     *
     * @param id individual id
     * @return
     * @throws
     * com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException {

        Individual ind = null;
        int flag = 0;
        Iterator<Individual> itr = iList.iterator();
        while (itr.hasNext()) {
            ind = itr.next();
            int a = id;
            int b = ind.getId();
            if (a == b) {
                flag = 1;
                break;
            }

        }
        if (flag == 1) {
            return ind;
        } else {
            throw new ObjectNotFoundException("Individual", "id", id.toString());
        }

    }

    /**
     * get individual object by name
     *
     * @param name
     * @return
     * @throws
     * com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException {

        Individual ind = null;
        Iterator<Individual> itr = iList.iterator();
        int flag = 0;

        while (itr.hasNext()) {
            ind = itr.next();
            String a = name;
            String b = ind.getName();

            if (a.equalsIgnoreCase(b)) {
                flag = 1;
                break;
            }

        }
        if (flag == 0) {
            throw new ObjectNotFoundException("Individual", "name", name);
        } else {
            return ind;
        }

    }

    /**
     * get a list of individual objects who are not active
     *
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals() {
        List<Individual> list1 = new ArrayList<>();
        Iterator<Individual> itr = iList.iterator();
        while (itr.hasNext()) {
            Individual ind = itr.next();
            Boolean a = false;
            Boolean b = ind.isActive();
            if (a == b) {
                list1.add(ind);
            }

        }
        return list1;
    }

    /**
     * get a list of individual objects who are active
     *
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals() {

        List<Individual> list1 = new ArrayList<>();

        Iterator<Individual> itr = iList.iterator();
        while (itr.hasNext()) {
            Individual ind = itr.next();
            Boolean a = true;
            Boolean b = ind.isActive();
            if (a == b) {
                list1.add(ind);
            }

        }
        return list1;

    }

    /**
     * get a list of team objects from db json
     *
     * @return
     */
    public List<Team> getListOfTeams() {

        return ListTeam;
    }
}
