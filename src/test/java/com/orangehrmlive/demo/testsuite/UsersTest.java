package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UsersTest extends Utility {
    AddUserPage addUserPage;
    AdminPage adminPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert;
    ViewSystemUsersPage viewSystemUsersPage;

    @BeforeMethod(groups = {"sanity","smoke","regression"})
    public void inIt() {
        addUserPage = new AddUserPage();
        adminPage = new AdminPage();
        softAssert = new SoftAssert();
        dashboardPage = new DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUsersPage = new ViewSystemUsersPage();
    }

    @Test(groups = {"smoke","regression"})
    public void adminShouldAddUserSuccessFully() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.CliclOnLoginButton();
        homePage.ClickOnAdmin();
        String expectedTest = ">";
        String actualTest = viewSystemUsersPage.verifySystemUsers();
        softAssert.assertEquals(expectedTest, actualTest);
        softAssert.assertAll();
        viewSystemUsersPage.clickOnAddButton();
        addUserPage.selectOnUserRoleDropDown("1");
        addUserPage.enterOnEmpName("Ananya Dash");
        addUserPage.EnterUserName("anand120378");
        addUserPage.selectstatusStatusDropdown("0");
        addUserPage.enterPassword("abc123");
        addUserPage.enterConfirmPassword("abc123");
        addUserPage.ClickOnSave();
    }

    @Test(groups = {"sanity","regression"})
    public void searchTheUserCreatedAndVerifyIt() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.CliclOnLoginButton();
        homePage.ClickOnAdmin();
        String expectedTest = ">";
        String actualTest = viewSystemUsersPage.verifySystemUsers();
        softAssert.assertEquals(expectedTest, actualTest);
        softAssert.assertAll();
        viewSystemUsersPage.searchOnSystemUser("anand120378");
        viewSystemUsersPage.selectSearchUserRoll("1");
        viewSystemUsersPage.selectSearchStatus("0");
        viewSystemUsersPage.clickOnSearchButton();
    }

    @Test(groups = {"sanity","regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.CliclOnLoginButton();
        homePage.ClickOnAdmin();
        String expectedTest = ">";
        String actualTest = viewSystemUsersPage.verifySystemUsers();
        softAssert.assertEquals(expectedTest, actualTest);
        softAssert.assertAll();
        viewSystemUsersPage.searchOnSystemUser("anand120378");
        viewSystemUsersPage.selectSearchUserRoll("1");
        viewSystemUsersPage.selectSearchStatus("0");
        viewSystemUsersPage.clickOnSearchButton();
        String expectedTest1 = "anand120378";
        String actualTest1 = viewSystemUsersPage.verifyUserShouldBeINResultList();
        softAssert.assertEquals(expectedTest1, actualTest1);
        softAssert.assertAll();
        viewSystemUsersPage.clickOnCheckBox();
        viewSystemUsersPage.clickOnDeleteButton();
        viewSystemUsersPage.clickokOnPopup();
        //verify is pending
    }

    @Test(groups = {"regression"})
    public void searchTheDeletedUserAndVerifyTheMessageNoRecordFound() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.CliclOnLoginButton();
        homePage.ClickOnAdmin();
        String expectedTest = ">";
        String actualTest = viewSystemUsersPage.verifySystemUsers();
        softAssert.assertEquals(expectedTest, actualTest);
        softAssert.assertAll();
        viewSystemUsersPage.searchOnSystemUser("anand120378");
        viewSystemUsersPage.selectSearchUserRoll("1");
        viewSystemUsersPage.selectSearchStatus("0");
        viewSystemUsersPage.clickOnSearchButton();
        String expectedAddedMsg3 = "No Records Found";//assert for added cart
        String realAddedMsg3 = viewSystemUsersPage.VerifyMsgNoRecordFound();
        Assert.assertEquals(realAddedMsg3, expectedAddedMsg3);
    }
}
