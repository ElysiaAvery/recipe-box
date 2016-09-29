import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class RecipeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void Recipe_instantiatesCorrectly_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertTrue(testRecipe instanceof Recipe);
  }

  @Test
  public void getName_instantiatesWithName_GrilledCheese() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertEquals("Grilled Cheese", testRecipe.getName());
  }

  @Test
  public void getAuthor_instantiatesWithAuthor_Sage() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertEquals("Sage", testRecipe.getAuthor());
  }

  @Test
  public void getIngredients_instantiatesWithIngredients_BreadCheese() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertEquals("Bread, Cheese", testRecipe.getIngredients());
  }

  @Test
  public void getInstructions_instantiatesWithInstructions_AddCheeseToBread() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertEquals("Add Cheese to Bread", testRecipe.getInstructions());
  }

  @Test
  public void equals_firstRecipeEqualsSecondRecipe_true() {
    Recipe firstRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    Recipe secondRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    assertTrue(firstRecipe.equals(secondRecipe));
  }

  @Test
  public void save_savesRecipeIntoDatabase_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    testRecipe.save();
    assertEquals(testRecipe, Recipe.all().get(0));
  }

  @Test
  public void all_returnsAllInstancesOfRecipe_true() {
    Recipe firstRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    firstRecipe.save();
    Recipe secondRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
    secondRecipe.save();
    assertEquals(firstRecipe, Recipe.all().get(0));
    assertEquals(secondRecipe, Recipe.all().get(1));
  }

  // @Test
  // public void update_updatesRecipeInformation_FondueGrillers() {
  //   Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread");
  //   testRecipe.save();
  //   assertEquals(testRecipe, Recipe.all().get(0));
  // }

}
