package com.seanroshan.superduperdrivecloudstorage;

import com.seanroshan.superduperdrivecloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotePage {


    @FindBy(xpath = "//a[contains(@id,'nav-notes-tab')]")
    private WebElement noteTab;


    @FindBy(xpath = "//button[contains(text(),'Add a New Note')]")
    private WebElement addNewNoteButton;


    @FindBy(xpath = "//input[@name='noteTitle']")
    private WebElement noteTitleField;

    @FindBy(xpath = "//textarea[@name='noteDescription']")
    private WebElement noteDescriptionTextArea;


    @FindBy(xpath = "//button[contains(@id,'note-submit-button')]")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[contains(text(),'Edit') and contains(@note-id,'')]")
    private WebElement editButton;

    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//td[contains(text(),'YOU DO NOT HAVE ANY NOTE')]")
    private WebElement noNoteText;


    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public void navigateToNoteTab() throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();
    }


    public Note getNote() throws InterruptedException {
        Thread.sleep(1000);
        Note note = new Note();
        note.setNoteTitle(editButton.getAttribute("note-title"));
        note.setNoteDescription(editButton.getAttribute("note-description"));
        return note;
    }

    public void addNewNote(Note note) throws InterruptedException {
        Thread.sleep(1000);
        this.addNewNoteButton.click();
        Thread.sleep(1000);
        this.noteTitleField.sendKeys(note.getNoteTitle());
        this.noteDescriptionTextArea.sendKeys(note.getNoteDescription());
        this.saveChangesButton.click();
    }

    public void editNote(Note note) throws InterruptedException {
        Thread.sleep(1000);
        this.editButton.click();
        Thread.sleep(1000);
        this.noteTitleField.clear();
        this.noteTitleField.sendKeys(note.getNoteTitle());
        this.noteDescriptionTextArea.clear();
        this.noteDescriptionTextArea.sendKeys(note.getNoteDescription());
        this.saveChangesButton.click();
    }

    public void deleteNote() throws InterruptedException {
        Thread.sleep(1000);
        this.deleteButton.click();
    }

    public String getNoNoteText() throws InterruptedException {
        Thread.sleep(1000);
        return this.noNoteText.getText();
    }

}
