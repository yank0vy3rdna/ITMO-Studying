abstract class APlace {
    private String placeName;
    APlace(String name) {
        placeName = name;
    }

	@Override
    public int hashCode() {
        return super.hashCode()+this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode()== this.hashCode() ? true : false;
    }

    @Override
    public String toString() {
    	return "Место " + this.getPlace();
    }
    String getPlace() {
        return placeName;
    }
}