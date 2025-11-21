package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class appointment_Pages {
    WebDriver driver;

    public appointment_Pages(WebDriver driver) {
        this.driver = driver;
    }

    // TC1: Validate Home Page Title and URL
    public boolean validateHomePageTitleAndURL() {
        String expectedTitle = "DanpheHealth";
        String expectedURL = "https://healthapp.yaksha.com/Home/Index/";
        return driver.getTitle().equals(expectedTitle) && driver.getCurrentUrl().equals(expectedURL);
    }

    // TC2: Ensure Appointment module is present and popup appears
    public boolean verifyAppointmentModulePresence() {
        driver.findElement(By.xpath("//button[@id='appointmentModule']")).click();
        WebElement popup = driver.findElement(By.xpath("//div[@id='selectCounterPopup']"));
        WebElement popupName = driver.findElement(By.xpath("//span[@class='popup-header' and text()='Select Counter']"));
        return popup.isDisplayed() && popupName.getText().equals("Select Counter");
    }

    // TC3: Verify "New Patient" button presence and info text reveal
    public boolean verifyNewPatientButtonAndInfo() {
        driver.findElement(By.xpath("//a[text()='New Visit']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'New Patient')]")).click();
        WebElement infoText = driver.findElement(By.xpath("//div[contains(text(),'Patient Information')]"));
        return infoText.isDisplayed();
    }

    // TC4: "Care of Person Contact" textbox presence (in Patient Info section)
    public boolean verifyCareOfPersonContactTextbox() {
        driver.findElement(By.xpath("//a[text()='New Visit']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Care of Person Contact']")).click();
        WebElement textbox = driver.findElement(By.xpath("//input[@placeholder='Care of Person Contact']"));
        return textbox.isDisplayed();
    }

    // TC5: Placeholder text for "Care of Person" textbox
    public boolean verifyCareOfPersonPlaceholderText() {
        WebElement textbox = driver.findElement(By.xpath("//input[@placeholder='Care Taker Person']"));
        String placeholder = textbox.getAttribute("placeholder");
        return placeholder.equals("Care Taker Person");
    }

    // TC6: Error message on empty "Patient Information" form (Print Invoice, Confirm)
    public boolean verifyErrorMessageOnEmptyForm() {
        driver.findElement(By.xpath("//button[text()='Print Invoice']")).click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
        WebElement errorMsg = driver.findElement(By.xpath("//div[@class='error' and contains(text(), 'Last Name is required')]"));
        return errorMsg.getText().contains("Last Name is required");
    }

    // TC7: Fill all Patient Info fields and validate displayed values
    public boolean validateEnteredPatientInformation(String fName, String mName, String lName, String age, String phone) {
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fName);
        driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys(mName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lName);
        driver.findElement(By.xpath("//input[@name='age']")).sendKeys(age);
        driver.findElement(By.xpath("//input[@name='phoneNo']")).sendKeys(phone);

        // Example: Reading back value for phone textbox
        String displayedPhone = driver.findElement(By.xpath("//input[@name='phoneNo']")).getAttribute("value");
        return displayedPhone.equals(phone);
    }

    // TC8: Presence of "Have DOB?" checkbox & "Datepicker" field
    public boolean verifyHaveDOBAndDatePickerPresence() {
        WebElement dobCheckbox = driver.findElement(By.xpath("//input[@type='checkbox' and @id='haveDOB']"));
        WebElement datePicker = driver.findElement(By.xpath("//input[@type='text' and contains(@class,'datepicker')]"));
        return dobCheckbox.isDisplayed() && datePicker.isDisplayed();
    }

    // TC9: Success notification message ("Success update")
    public boolean verifySuccessNotification() {
        driver.findElement(By.xpath("//input[@type='checkbox' and @id='external']")).click();
        driver.findElement(By.xpath("//button[text()='Add']")).click();
        WebElement successMsg = driver.findElement(By.xpath("//div[@class='notification' and contains(text(), 'Success update')]"));
        return successMsg.getText().contains("Success update");
    }
}
