package dev.nasim.entities;


    import java.util.Objects;

    public class Manager {
        private int managerId;
        private String firstName;
        private String lastName;
        private String username;
        private String pswrd;

        public Manager(){
            this.managerId = 0;
            this.firstName = "";
            this.lastName = "";
            this.username = "";
            this.pswrd = "";
        }

        public Manager(int managerId, String username, String pswrd){
            this.managerId = managerId;
            this.username = username;
            this.pswrd = pswrd;
            this.firstName = "";
            this.lastName = "";
        }

        public Manager(int managerId, String firstName, String lastName, String username, String pswrd) {
            this.managerId = managerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.pswrd = pswrd;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPswrd() {
            return pswrd;
        }

        public void setPswrd(String pswrd) {
            this.pswrd = pswrd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Manager manager = (Manager) o;
            return getManagerId() == manager.getManagerId() && Objects.equals(getFirstName(), manager.getFirstName()) && Objects.equals(getLastName(), manager.getLastName()) && Objects.equals(getUsername(), manager.getUsername()) && Objects.equals(getPswrd(), manager.getPswrd());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getManagerId(), getFirstName(), getLastName(), getUsername(), getPswrd());
        }

        @Override
        public String toString() {
            return "Manager{" +
                    "managerId=" + managerId +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", username='" + username + '\'' +
                    ", pswrd='" + pswrd + '\'' +
                    '}';
        }


    }

