# Install Django
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
sudo python get-pip.py
sudo pip install Django

# Creates project
django-admin startproject ${projectName}

# Runs server
python manage.py runserver 20002
w3m http://localhost:20002

# Creates app
python manage.py startapp ${appName}



