package org.example;

public class Article implements Comparable<Article> {
    private int id;
    private String title;
    private String articleAbstract;
    private int csType; //Computer Science genre 1 or 0
    private int physicsType;
    private int mathType;
    private int statsType; //Statistics genre 1 or 0
    private int qbType; //Quantitative Biology 1 or 0
    private int qfType; // Quantitative Finance 1 or 0

    public Article(int id, String title, String articleAbstract, int csType, int physicsType, int mathType, int statsType, int qbType, int qfType) {
        this.id = id;
        this.title = title;
        this.articleAbstract = articleAbstract;
        this.csType = csType;
        this.physicsType = physicsType;
        this.mathType = mathType;
        this.statsType = statsType;
        this.qbType = qbType;
        this.qfType = qfType;
    }

    public Article(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public int getCsType() {
        return csType;
    }

    public void setCsType(int csType) {
        this.csType = csType;
    }

    public int getPhysicsType() {
        return physicsType;
    }

    public void setPhysicsType(int physicsType) {
        this.physicsType = physicsType;
    }

    public int getMathType() {
        return mathType;
    }

    public void setMathType(int mathType) {
        this.mathType = mathType;
    }

    public int getStatsType() {
        return statsType;
    }

    public void setStatsType(int statsType) {
        this.statsType = statsType;
    }

    public int getQbType() {
        return qbType;
    }

    public void setQbType(int qbType) {
        this.qbType = qbType;
    }

    public int getQfType() {
        return qfType;
    }

    public void setQfType(int qfType) {
        this.qfType = qfType;
    }

    @Override
    public int compareTo(Article o) {
        //Compares IDx vs IDy, whereby IDy is o.id
        //Returns 0 if IDx is equal to IDy
        //Returns -1 if IDx is less than IDy
        //Returns difference if IDx is greater than IDy
        int i = Integer.compare(this.id,o.id);
        if(i==0){
            return 0;
        }else if(i>0){
            return this.id - o.id;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "ID: "+ this.id + "\nTitle: "+this.title +"\nAbstract: "+ this.articleAbstract + "\nArticle types: "
                +this.csType+" "+ this.physicsType+" " +this.mathType+" " +this.statsType+" " +this.qbType+" " +this.qfType;
    }
}
