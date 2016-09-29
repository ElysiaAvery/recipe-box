import java.util.List;
import org.sql2o.*;


public class Recipe implements DatabaseManagement {
  private int id;
  private String name;
  private String author;
  private String ingredients;
  private String instructions;


  public Recipe(String name, String author, String ingredients, String instructions) {
    this.name = name;
    this.author = author;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getIngredients() {
    return ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  @Override
  public boolean equals(Object otherRecipe) {
    if(!(otherRecipe instanceof Recipe)) {
      return false;
    } else {
      Recipe newRecipe = (Recipe) otherRecipe;
      return this.getName().equals(newRecipe.getName()) &&
             this.getAuthor().equals(newRecipe.getAuthor()) &&
             this.getIngredients().equals(newRecipe.getIngredients()) &&
             this.getInstructions().equals(newRecipe.getInstructions()) &&
             this.getId() == newRecipe.getId();
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes (name, author, ingredients, instructions) VALUES (:name, :author, :ingredients, :instructions)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("author", this.author)
      .addParameter("ingredients", this.ingredients)
      .addParameter("instructions", this.instructions)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Recipe> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes";
      return con.createQuery(sql)
      .executeAndFetch(Recipe.class);
    }
  }

  public static Recipe find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes WHERE id = :id";
      Recipe recipe = con.createQuery(sql)
                         .addParameter("id", id)
                         .executeAndFetchFirst(Recipe.class);
      return recipe;
    }
  }

  public void update(String name, String author, String ingredients, String instructions) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE recipes SET name = :name, author = :author, ingredients = :ingredients, instructions = :instructions WHERE id = :id";
      con.createQuery(sql)
         .addParameter("id", this.id)
         .addParameter("name", name)
         .addParameter("author", author)
         .addParameter("ingredients", ingredients)
         .addParameter("instructions", instructions)
         .executeUpdate();
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM recipes WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

}
