public class Record {
    private String category;
    private int subCategory;

    public Record(String category,int subCategory) {
        this. category = category;
        this.subCategory = subCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(category).append(",").append(subCategory);
        return stringBuilder.toString();
    }
}