package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "uploadButton")
    private WebElement uploadButton;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "noteListTitle")
    private WebElement noteListTitle;

    @FindBy(id = "noteListDescription")
    private WebElement noteListDescription;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteEdit")
    private WebElement noteEdit;

    @FindBy(id = "noteDelete")
    private WebElement noteDelete;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "saveChanges")
    private WebElement saveChanges;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSaveChanges")
    private WebElement credentialSaveChanges;

    @FindBy(id = "credentialListURL")
    private WebElement credentialListURL;

    @FindBy(id = "credentialListUsername")
    private WebElement credentialListUsername;

    @FindBy(id = "credentialListPassword")
    private WebElement credentialListPassword;

    @FindBy(id = "credentialEdit")
    private WebElement credentialEdit;

    @FindBy(id = "credentialDelete")
    private WebElement credentialDelete;

    @FindBy(id = "credentialModalClose")
    private WebElement credentialModalClose;

    @FindBy(id = "credentialModalPassword")
    private WebElement credentialModalPassword;

    private WebDriverWait wait;


    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 500);
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(this.logoutButton)).click();
    }

    public void goToNoteTab() {
        wait.until(ExpectedConditions.elementToBeClickable(this.navNotesTab)).click();
    }

    public void goToCredentialTab() {
        wait.until(ExpectedConditions.elementToBeClickable(this.navCredentialsTab)).click();
    }

    public void addNewNote(String title, String description) {
        wait.until(ExpectedConditions.elementToBeClickable(this.addNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).sendKeys(description);
        wait.until(ExpectedConditions.elementToBeClickable(this.saveChanges)).click();
    }

    public void addNewCredential(String url, String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(this.addCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialURL)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialSaveChanges)).click();
    }

    public Note getNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(this.noteListTitle)).getText();
        String description = wait.until(ExpectedConditions.elementToBeClickable(this.noteListDescription)).getText();
        return new Note(0, title, description, 0);
    }

    public Credential getCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(this.credentialListURL)).getText();
        String username = wait.until(ExpectedConditions.elementToBeClickable(this.credentialListUsername)).getText();
        String password = wait.until(ExpectedConditions.elementToBeClickable(this.credentialListPassword)).getText();

        return new Credential(0, url, username, "", password, 0);
    }

    public void editNote(String title, String description) {
        wait.until(ExpectedConditions.elementToBeClickable(this.noteEdit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).sendKeys(description);
        wait.until(ExpectedConditions.elementToBeClickable(this.saveChanges)).click();
    }

    public void editCredential(String url, String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialEdit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialURL)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialURL)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialSaveChanges)).click();
    }

    public void deleteNote() {
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDelete)).click();
    }

    public void deleteCredential() {
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialDelete)).click();
    }

    public boolean isNoteExist(WebDriver webDriver) {
        try {
            webDriver.findElement(By.id("noteListTitle"));
            webDriver.findElement(By.id("noteListDescription"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

    public boolean isCredentialExist(WebDriver webDriver) {
        try {
            webDriver.findElement(By.id("credentialListURL"));
            webDriver.findElement(By.id("credentialListUsername"));
            webDriver.findElement(By.id("credentialListPassword"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

    public String getDecryptedPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialEdit)).click();
        String decryptedPassword = wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).getAttribute("value");
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialModalClose)).click();
        return decryptedPassword;
    }

}
