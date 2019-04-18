from faker import Faker
import pandas as pd
import random
import sys

faker = Faker()
DataTuple = []
Characters = ["Fox","Falco","Marth","Peach","Sheik","Captain Falcon","Ice Climbers","Jigglypuff","Luigi","Ganondorf","Young Link"]
Stages = ["Battlefield","Final Destination","Dreamland N64","Fountain of Dreams","Yoshi's Story","Pokemon Stadium"]

print("How many tuples would you like to create?")
tupleNum = int(input());


for i in range(0, tupleNum):
    firstName = (faker.first_name())
    lastName = (faker.last_name())
    region = (faker.country())
    main = Characters[random.randint(0,10)]
    favStage = Stages[random.randint(0,5)]
    ssn = (faker.itin())
    job = (faker.job())
    jobCountry = region
    jobState = (faker.state_abbr(include_territories=True))
    jobCity = (faker.city())
    jobZip = faker.zipcode_in_state(state_abbr=jobState)

    DataTuple.insert(i, [firstName, lastName, region, main, favStage, ssn, job, jobCountry, jobState, jobCity, jobZip])

print("Output filename: ")
fileName = input();

df = pd.DataFrame(DataTuple)
df.to_csv(fileName + '.csv', index=False, header=False)
print(df)
