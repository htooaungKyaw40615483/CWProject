package com.napier.sem;

public class Country
{
        /*** Country Code */
        private String country_no;
        /*** Country name */
        private String country_name;
        /*** Continent */
        private String continent_name;
        /*** Region */
        private String region_name;
        /** * Country's population */
        private int population;
        /** * Country's Capital */
        private String capital_name;

        // Getters for accessing the class attributes only for reading values.

        public String getCountry_no() {
                return country_no;
        }

        public String getCountry_name() {
                return country_name;
        }

        public String getContinent_name() {
                return continent_name;
        }

        public String getRegion_name() {
                return region_name;
        }

        public int getPopulation() {
                return population;
        }

        public String getCapital_name() {
                return capital_name;
        }

        // Setters for accessing the class attributes only for changing/inserting values.

        public void setCountry_no(String country_no) {
                this.country_no = country_no;
        }

        public void setCountry_name(String country_name) {
                this.country_name = country_name;
        }

        public void setContinent_name(String continent_name) {
                this.continent_name = continent_name;
        }

        public void setRegion_name(String region_name) {
                this.region_name = region_name;
        }

        public void setPopulation(int population) {
                this.population = population;
        }

        public void setCapital_name(String capital_name) {
                this.capital_name = capital_name;
        }
}
