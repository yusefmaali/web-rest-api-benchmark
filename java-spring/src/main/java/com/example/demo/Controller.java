package com.example.demo;

import java.util.*;

import com.example.demo.model.Country;
import com.example.demo.model.User;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class Controller {

    private CountryRepository repository;
    private UserRepository users;

    public Controller(CountryRepository countries, UserRepository users) {
        this.repository = countries;
        this.users = users;
    }

    @RequestMapping(value = "/hello", produces = "application/json")
    public String hello() {
        return new JSONObject().put("hello", "world").toString();
    }

    @RequestMapping(value = "/compute", produces = "application/json")
    public String compute() {
        long x = 0, y = 1, z, max;

        Random r = new Random();
        max = 10000 + r.nextInt(500);

        for (int i = 0; i <= max; i++) {
            z = x + y;
            x = y;
            y = z;
        }

        return new JSONObject().put("status", "done").toString();
    }

    @RequestMapping(value = "/countries", produces = "application/json")
    @Transactional
    public List<Country> countries() {
        return repository.findAll();
    }

    @RequestMapping(value = "/users", produces = "application/json")
    @Transactional
    public List<User> users() {
        return users.findByCountries_Name("France");
    }

    @RequestMapping(value = "/db", produces = "text/plain")
    public String db() {
        int countriesMax = 190;
        int max = 5;
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 1; i <= 1100; i++) {
            int count = r.nextInt(max) + 1;
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 1; j <= count; j++) {
                int v = r.nextInt(countriesMax) + 1;
                map.put(v, v);
            }
            List<Integer> set = new ArrayList<>(map.keySet());
            set.sort(Comparator.comparingInt(value -> value));
            for (Integer integer : set) {
                sb.append("(").append(i).append(",").append(integer).append("),\n");
            }
        }

        return sb.toString();
    }
}
