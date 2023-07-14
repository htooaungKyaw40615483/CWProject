package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class Country
{
        // Country Code
        private String country_no;
        // Country Name
        private String country_name;
        // Continent Name
        private String continent_name;
        // Region Name
        private String region_name;
        // Country's Population
        private int population;
        // Capital City of Country
        private String capital_name;


        // Getters for accessing the class attributes only for reading values.

        /**
         * Getting the Country Code of the Class
         * @return Country Code
         */
        public String getCountry_no() {
                return country_no;
        }
        /**
         * Getting the Country Name of the Class
         * @return Country Name
         */
        public String getCountry_name() {
                return country_name;
        }
        /**
         * Getting the Continent Name of the Class
         * @return Continent Name of the Country
         */
        public String getContinent_name() {
                return continent_name;
        }
        /**
         * Getting the Region Name of the Class
         * @return Region Name of the Country
         */
        public String getRegion_name() {
                return region_name;
        }
        /**
         * Getting the Country Population of the Class
         * @return Country's Population
         */
        public int getPopulation() {
                return population;
        }
        /**
         * Getting the Country Capital City Name of the Class
         * @return Country's Capital City Name
         */
        public String getCapital_name() {
                return capital_name;
        }


        // Setters for accessing the class attributes only for changing/inserting values.

        /**
         * Setting the Country Code of the Class
         * @param country_no Country Code
         */
        public void setCountry_no(String country_no) {
                this.country_no = country_no;
        }
        /**
         * Setting the Country Name of the Class
         * @param country_name Country Name
         */
        public void setCountry_name(String country_name) {
                this.country_name = country_name;
        }
        /**
         * Setting the Continent Name of the Class
         * @param continent_name Continent Name of the country
         */
        public void setContinent_name(String continent_name) {
                this.continent_name = continent_name;
        }
        /**
         * Setting the Region Name of the Class
         * @param region_name Region Name of the country
         */
        public void setRegion_name(String region_name) {
                this.region_name = region_name;
        }
        /**
         * Setting the Country's Population of the Class
         * @param population Country Population
         */
        public void setPopulation(int population) {
                this.population = population;
        }
        /**
         * Setting the Country's Capital City Name of the Class
         * @param capital_name Country's Capital City Name
         */
        public void setCapital_name(String capital_name) {
                this.capital_name = capital_name;
        }
}
