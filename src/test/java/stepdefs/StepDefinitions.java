package stepdefs;

import java.util.List;
import org.assertj.core.api.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ToDoHomePage;

public class StepDefinitions {	
	
	ToDoHomePage toDoHomePage;
	
	@Given("I am on home page")
	public void i_am_on_home_page() {
		toDoHomePage.open();
		toDoHomePage.assertToDoHomePageIsDisplayed();
	}
	
	@When("I add {string} to Todo list")
	public void i_add_to_Todo_list(String actionItem) {
		toDoHomePage.EnterTextInSearchBox(actionItem);
	}
	
	@Then("I should see Todo list contains")
	public void i_should_see_Todo_list_contains(List<String>expectedItems) {
		Assertions.assertThat(toDoHomePage.addedItems()).isEqualTo(expectedItems);
	}
	
	@Then("I should see Todo list summary as {string}")
	public void i_should_see_todo_list_summary_as(String expectedItemsLeftMessage) {
		Assertions.assertThat(toDoHomePage.numberOfItemsLeftMessage()).isEqualTo(expectedItemsLeftMessage);
	}

	@When("I add below items to Todo list")
	public void i_add_below_items_to_todo_list(List<String>actionItems) {
		for(String actionItem: actionItems) {
			toDoHomePage.EnterTextInSearchBox(actionItem);
		}
	}
	
	@When("I click on active tab")
	public void i_click_on_active_tab() {
		toDoHomePage.clickOnActiveTab();
	}
	
	@When("I click on completed tab")
	public void i_click_on_completed_tab() {
		toDoHomePage.clickOnCompletedTab();
	}
	
	@Then("I should see Todo list is empty")
	public void i_should_see_todo_list_is_empty() {
		Assertions.assertThat(toDoHomePage.addedItems().isEmpty());
	}

	@When("I filter the list to show {string} tasks")
	public void i_filter_the_list_to_show_tasks(String label) {
		toDoHomePage.clickOnFilterButtonWithLabel(label);
	}
	
	@Then("I should NOT see Todo list contains {string} in {string} filter")
	public void i_should_not_see_todo_list_contains_in_filter(String completedItem, String label) {
		toDoHomePage.clickOnFilterButtonWithLabel(label);
		Assertions.assertThat(!toDoHomePage.addedItems().contains(completedItem));
	}
	
	@When("I click on button Clear Completed")
	public void i_click_on_button_clear_completed() {
		toDoHomePage.clickOnClearCompletedButton();
	}

	@When("I complete below items from Todo list")
	public void i_complete_below_items_from_todo_list(List<String>items) {
	    for(String item: items) {
	    	toDoHomePage.completeItem(item);
	    }
	}

	@When("I complete all the item from Todo list")
	public void i_complete_all_the_item_from_todo_list() {
		toDoHomePage.clickOnToggleAllLabel();
	}

	@Then("I should NOT see Active, Completed and Clear Completed")
	public void i_should_not_see_active_completed_and_clear_completed() {
		toDoHomePage.assertActiveButtonIsNotDisplayed();
		toDoHomePage.assertCompletedButtonIsNotDisplayed();
		toDoHomePage.assertClearCompletedButtonIsNotDisplayed();
	}
}
