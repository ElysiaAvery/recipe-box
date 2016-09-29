import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;


public class Recipe implements DatabaseManagement {
  private int id;
  private String name;
  private String author;
  private String ingredients;
  private String instructions;
  private int servings;
  private Timestamp created_at;

  public static final int MIN_SERVINGS = 1;


  public Recipe(String name, String author, String ingredients, String instructions, int servings) {
    this.name = name;
    this.author = author;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.servings = servings;
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

  public int getServings() {
    return servings;
  }

  public Timestamp getCreatedAt() {
    return created_at;
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
             this.getServings() == newRecipe.getServings() &&
             this.getId() == newRecipe.getId();
    }
  }

  @Override
  public void save() {
    if (servings < MIN_SERVINGS) {
      throw new UnsupportedOperationException("You must at least make one servings worth!");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes (name, author, ingredients, instructions, created_at, servings) VALUES (:name, :author, :ingredients, :instructions, now(), :servings)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("author", this.author)
      .addParameter("ingredients", this.ingredients)
      .addParameter("instructions", this.instructions)
      .addParameter("servings", this.servings)
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

  public void update(String name, String author, String ingredients, String instructions, int servings) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE recipes SET name = :name, author = :author, ingredients = :ingredients, instructions = :instructions, servings = :servings WHERE id = :id";
      con.createQuery(sql)
         .addParameter("id", this.id)
         .addParameter("name", name)
         .addParameter("author", author)
         .addParameter("ingredients", ingredients)
         .addParameter("instructions", instructions)
         .addParameter("servings", servings)
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
