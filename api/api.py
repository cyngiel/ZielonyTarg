import matplotlib
import requests
import json
import pandas as pd

def jprint(obj):
    text = json.dumps(obj, sort_keys=True, indent=4)
    print(text)

def getJSONdata(href):
    response = requests.get(href)
    return response.json()['data']

def getCSVlink(href):
    link = ''
    response = requests.get(href)
    data = response.json()['data']

    for key in data.keys():
        if key == 'attributes':
            data = data[key]
            for k in data:
                if k == 'csv_file_url':
                    link = data[k]
    return link

def getCategories(url_csv):
    # Czytanie pliku csv dla kategorii
    csv = pd.read_csv(url_csv, encoding='utf-8', usecols=[1])
    csv = csv.values.tolist()

    #Zapisanie kategorii produktów do listy (bez powtórzeń)
    categories = list()

    for category in csv:
        if category not in categories:
            x = str(category)
            #wykluczenie powotrzen przez dodatkowa spacje na koncu niektorych nazw
            if x[-3] != " ":
                categories.append(category)

    #usuniecie [nan]
    categories = categories[:-1]
    return categories

def getVoivodeships(url_csv):
    # Czytanie pliku csv dla kategorii
    csv = pd.read_csv(url_csv, encoding='utf-8', usecols=[0])
    csv = csv.values.tolist()

    #Zapisanie kategorii produktów do listy (bez powtórzeń)
    voivodeships = list()

    for voivodeship in csv:
        if voivodeship not in voivodeships:
            voivodeships.append(voivodeship)

    #usuniecie [nan]
    voivodeships = voivodeships[:-1]
    return voivodeships

def writeListToFile(dataList, filepath):
    f = open(filepath, "w")
    for item in dataList:
        f.write(str(item)[2:-2]+'\n')
    f.close()
def main():
    url = "https://api.dane.gov.pl/resources/26432,produkty-wpisane-na-liste-produktow-tradycyjnych-stan-na-dzien-16102020-r"
    #Wyswietlenie danych z API
    #jprint(getJSONdata(url))

    #Wyselekcjonowanie linku do pliku csv z API
    url_csv = getCSVlink(url)

    categories = getCategories(url_csv)
    print(categories)

    voivodeships = getVoivodeships(url_csv)
    print(voivodeships)

    #zapis do pliku txt do czasu stworzenia bazy danych
    writeListToFile(categories, "categories.txt")

if __name__ == "__main__":
    main()