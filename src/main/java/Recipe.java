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

  }

  @Override
  public void update() {

  }

  @Override
  public void delete() {

  }

}
