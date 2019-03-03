package com.example.admin.techkid_session4.network;

import java.util.List;

public class MusicTypesResponse {
    public List<MusicTypeJSON> subgenres;

    public class MusicTypeJSON {
        public String id;
        public String translation_key;

        @Override
        public String toString() {
            return "MusicTypeJSON{" +
                    "id='" + id + '\'' +
                    ", translation_key='" + translation_key + '\'' +
                    '}';
        }
    }
}
