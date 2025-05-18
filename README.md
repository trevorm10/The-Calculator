# The-Calculator
# steps to follow when intializing a new branch
git init
git add .
git commit -m 'write what you committed'
git branch -M main
git remote add origin <copy the repo URL here> example. https://github.com/trevorm10/The-Calculator.git
git push -u origin main

# this is switching to branch and committing
git checkout -b secondleg
git add .
git commit -m 'write what you committed'
git push -u origin secondleg

# this is switching to branch 
git checkout main
