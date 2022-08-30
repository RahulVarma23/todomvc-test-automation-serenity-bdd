package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://todomvc.com/examples/vue")
public class ToDoHomePage extends PageObject{

	@FindBy(xpath="//h1[text() = 'todos']")
	WebElementFacade header; 
	
	@FindBy(css=".new-todo")
	WebElementFacade searchbox; 
	
	@FindBy(css=".todo-count")
	WebElementFacade numberOfItemsLeftText;
	
	@FindBy(css="a[href='#/active']")
	WebElementFacade activeButton;
	
	@FindBy(css="a[href='#/completed']")
	WebElementFacade completedButton;
	
	@FindBy(css=".clear-completed")
	WebElementFacade clearCompletedButton;
	
	@FindBy(css="label[for=toggle-all]")
	WebElementFacade toggleAllLabel;
	
	private final String filterBtnWithLabelLocator = "%s";
	private final String completedItemCheckBoxLocator = "//ul[@class='todo-list']//li[contains(.,'%s')]//input[@type='checkbox']";
	By allToDoItems = By.cssSelector(".view");
	
	JavascriptExecutor js = (JavascriptExecutor)getDriver();
	Actions actions = new Actions(getDriver());
	
	public WebElementFacade getFilterButtonWithLabel(String label) {
		return find(By.linkText(String.format(filterBtnWithLabelLocator, label)));
	}
	
	public WebElementFacade getCompletedItemCheckboxFor(String item) {
        return find(By.xpath(String.format(completedItemCheckBoxLocator, item)));
    }
	
	@Step
    public void assertToDoHomePageIsDisplayed() {
		header.waitUntilVisible();
    }

	@Step
	public void EnterTextInSearchBox(String actionItem) {
		searchbox.type(actionItem).then().sendKeys(Keys.ENTER);
	}
	
	@Step
	public List<String> addedItems() {
        return findAll(allToDoItems)
                .stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }
	
	@Step
	public String numberOfItemsLeftMessage() {
        return numberOfItemsLeftText.getText();
    }
	
	@Step
	public void clickOnActiveTab() {
		this.activeButton.click();
	}
	
	@Step
	public void clickOnCompletedTab() {
		this.completedButton.click();
	}
	
	@Step
	public void clickOnFilterButtonWithLabel(String label) {
		this.getFilterButtonWithLabel(label).click();
	}
	
	@Step
	public void completeItem(String completedItem) {
        this.getCompletedItemCheckboxFor(completedItem).click();
    }

	@Step
	public void clickOnClearCompletedButton() {
		this.clearCompletedButton.click();	
	}
	
	@Step
	public void clickOnToggleAllLabel() {
		this.toggleAllLabel.click();
	}
	
	@Step
	public void assertActiveButtonIsNotDisplayed() {
		this.activeButton.shouldNotBeVisible();
	}
	
	@Step
	public void assertCompletedButtonIsNotDisplayed() {
		this.completedButton.shouldNotBeVisible();
	}
	
	@Step
	public void assertClearCompletedButtonIsNotDisplayed() {
		this.clearCompletedButton.shouldNotBeVisible();
	}
}
