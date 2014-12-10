When(/^The user types in a valid 'nickname'$/) do
  sleep(2)
  test=2
end

When(/^The user types in a valid 'email'$/) do
 sleep(3)
   test=2

end

When(/^The user types in a valid 'password'$/) do
  sleep(1)
     test=2

end

When(/^The user repeats a valid 'repeat'$/) do
  sleep(3)
     test=2

end

When(/^The user accepts our 'termsOfUse'$/) do
  test=2
end

When(/^The user hits the submit button$/) do
  test=2
end

Then(/^A new account gets created$/) do
  sleep(1)
  test=3
end

Then(/^All the data is stored in our database$/) do
  test=2 # express the regexp above with the code you wish you had
end

When(/^The user types in his valid nickname$/) do
 test=3
end

When(/^The user types in an invalid e\-mail\-address$/) do
  sleep(3)
     test=2

end

When(/^The user types in his valid password$/) do
   test=2
end

When(/^The user repeats his valid password$/) do
 test=4
end

When(/^The user accepts our terms of use$/) do
  sleep(1)
end

Then(/^An error message that tells the user that the email is invalid gets displayed$/) do
  test=2
end

Then(/^Nothing is stored in our database$/) do
 sleep(3)
    test=2

end

Then(/^No account gets created$/) do
  test=2
end

When(/^The user types in an valid e\-mail\-address$/) do
 sleep(3)
    test=2

end

When(/^The user does not repeat the password correctly$/) do
   test=2
end

Then(/^An error message gets displayed to the user that his repeated password is invalid$/) do
  test=2
end

Given(/^The logged in user clicks on the button 'Edit' in his profile$/) do
  test=2
end

Given(/^his email\-address gets displayed$/) do
  test=2
end

When(/^The user types in his valid old password$/) do
   test=2
end

When(/^The user types in his valid new password$/) do
  test=2
end

When(/^The user repeats his valid new password$/) do
  test=2
end

When(/^The user types in his invalid old password$/) do
   test=2
end

Then(/^The user gets an error message that his old password is incorrect$/) do
  test=2
end

Then(/^Nothing gets changed in our database$/) do
  sleep(3)
     test=2

end

Then(/^The email in the database is manipulated, even if it has not been changed, for this user$/) do
   sleep(1)
   test=2
end

Then(/^The password in the database is manipulated for this user$/) do
  test=2
end

Then(/^The user gets a confirmation message on his browser window displayed$/) do
   test=2
end

When(/^The user types in his valid new email\-address$/) do
 sleep(3)
    test=2

end

When(/^The user types in valid his old password$/) do
   test=2
end

Then(/^The email in the database is manipulated, even if it has not been changed, for this user$/) do
  test=2
end

When(/^The user types in his invalid new email\-address$/) do
   test=2
end

Then(/^The user gets an error message that his new email\-address is incorrect$/) do
   test=2
  sleep(3)
end

Given(/^there are several character for choosing$/) do
  sleep(3)
     test=2

end

When(/^you click on 'Bob' as character$/) do
  test=2
end

When(/^you click on the button 'Start Game' on the left side$/) do
   test=2
end

Then(/^the game starts$/) do
   test=2
end

When(/^you click on 'Alice' as character$/) do
   test=2
end

When(/^you not click on any character$/) do
sleep(2)
  test=2
end

Then(/^the game does not  start$/) do
  test=2
end