package model;

import java.util.ArrayList;

public class SuperHeroes {
    public String squadName;
    public ArrayList<Member> members;

    public static class Member {
        public String name;
        public int age;
        public String secretIdentity;
        public ArrayList<String> powers;
    }
}
