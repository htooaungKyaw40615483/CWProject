package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class Country
{
        // Country Code
        private String countryNo;
        // Country Name
        private String countryName;
        // Continent Name
        private String continentName;
        // Region Name
        private String regionName;
        // Country's Population
        private int population;
        // Capital City of Country
        private String capitalName;


        // Getters for accessing the class attributes only for reading values.

        /**
         * Getting the Country Code of the Class
         * @return Country Code
         */
        public String getCountryNo() {
                return countryNo;
        }
        /**
         * Getting the Country Name of the Class
         * @return Country Name
         */
        public String getCountryName() {
                return countryName;
        }
        /**
         * Getting the Continent Name of the Class
         * @return Continent Name of the Country
         */
        public String getContinentName() {
                return continentName;
        }
        /**
         * Getting the Region Name of the Class
         * @return Region Name of the Country
         */
        public String getRegionName() {
                return regionName;
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
        public String getCapitalName() {
                return capitalName;
        }


        // Setters for accessing the class attributes only for changing/inserting values.

        /**
         * Setting the Country Code of the Class
         * @param countryNo Country Code
         */
        public void setCountryNo(String countryNo) {
                this.countryNo = countryNo;
        }
        /**
         * Setting the Country Name of the Class
         * @param countryName Country Name
         */
        public void setCountryName(String countryName) {
                this.countryName = countryName;
        }
        /**
         * Setting the Continent Name of the Class
         * @param continentName Continent Name of the country
         */
        public void setContinentName(String continentName) {
                this.continentName = continentName;
        }
        /**
         * Setting the Region Name of the Class
         * @param regionName Region Name of the country
         */
        public void setRegionName(String regionName) {
                this.regionName = regionName;
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
         * @param capitalName Country's Capital City Name
         */
        public void setCapitalName(String capitalName) {
                this.capitalName = capitalName;
        }
}
