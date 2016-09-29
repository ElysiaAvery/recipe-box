import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class RecipeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void Recipe_instantiatesCorrectly_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertTrue(testRecipe instanceof Recipe);
  }

  @Test
  public void getName_instantiatesWithName_GrilledCheese() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertEquals("Grilled Cheese", testRecipe.getName());
  }

  @Test
  public void getAuthor_instantiatesWithAuthor_Sage() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertEquals("Sage", testRecipe.getAuthor());
  }

  @Test
  public void getIngredients_instantiatesWithIngredients_BreadCheese() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertEquals("Bread, Cheese", testRecipe.getIngredients());
  }

  @Test
  public void getInstructions_instantiatesWithInstructions_AddCheeseToBread() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertEquals("Add Cheese to Bread", testRecipe.getInstructions());
  }

  @Test
  public void equals_firstRecipeEqualsSecondRecipe_true() {
    Recipe firstRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    Recipe secondRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    assertTrue(firstRecipe.equals(secondRecipe));
  }

  @Test
  public void save_savesRecipeIntoDatabase_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    testRecipe.save();
    assertEquals(testRecipe, Recipe.all().get(0));
  }

  @Test
  public void all_returnsAllInstancesOfRecipe_true() {
    Recipe firstRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    firstRecipe.save();
    Recipe secondRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    secondRecipe.save();
    assertEquals(firstRecipe, Recipe.all().get(0));
    assertEquals(secondRecipe, Recipe.all().get(1));
  }

  @Test
  public void find_returnsRecipeWithSameId_secondId() {
    Recipe firstRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    firstRecipe.save();
    Recipe secondRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    secondRecipe.save();
    assertEquals(secondRecipe, Recipe.find(secondRecipe.getId()));
  }

  @Test
  public void update_updatesRecipeInformation_FondueGrillers() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    testRecipe.save();
    testRecipe.update("Fondue Grillers", "Hero", "Pilsbury Croissants, Velveeta Cheese", "Add cheese to croissants", 1);
    Recipe updateRecipe = Recipe.find(testRecipe.getId());
    assertEquals("Fondue Grillers", updateRecipe.getName());
    assertEquals("Hero", updateRecipe.getAuthor());
    assertEquals("Pilsbury Croissants, Velveeta Cheese", updateRecipe.getIngredients());
    assertEquals("Add cheese to croissants", updateRecipe.getInstructions());
  }

  @Test
  public void delete_deletesRecipeInformation_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread" , 1);
    testRecipe.save();
    testRecipe.delete();
    assertEquals(null, Recipe.find(testRecipe.getId()));
  }

  @Test
  public void getCreatedAt_getTimeOfRecipeCreation() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 1);
    testRecipe.save();
    Timestamp savedRecipe = Recipe.find(testRecipe.getId()).getCreatedAt();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(savedRecipe.getDay(), rightNow.getDay());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void recipe_cannotDecrementBelowMINSERVINGS_true() {
    Recipe testRecipe = new Recipe("Grilled Cheese", "Sage", "Bread, Cheese", "Add Cheese to Bread", 0);
    testRecipe.save();
  }

}
