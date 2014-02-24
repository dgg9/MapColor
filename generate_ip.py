from random import randrange


states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New_Hampshire', 'New_Jersey', 'New_Mexico', 'New_York', 'North_Carolina', 'North_Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode_Island', 'South_Carolina', 'South_Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West_Virginia', 'Wisconsin', 'Wyoming']

cities = ['Oklahoma_City', 'Houston', 'Phoenix', 'Los_Angeles', 'San_Antonio', 'Buckeye', 'Dallas', 'Fort_Worth', 'San_Diego', 'Memphis', 'Kansas_City', 'New_York_City', 'Austin', 'Charlotte', 'El_Paso', 'Chicago', 'Tucson', 'Columbus', 'Valdez', 'Huntsville', 'Boulder_City', 'California_City', 'Tulsa', 'Colorado_Springs', 'Goodyear', 'Albuquerque', 'Scottsdale',  'Hibbing', 'Norman', 'San_Jose', 'Peoria', 'Corpus_Christi', 'Montgomery', 'Wichita', 'Aurora', 'Sierra_Vista', 'Birmingham', 'Fayetteville', 'Carson_City', 'Raleigh', 'Bakersfield', 'Mobile', 'Detroit', 'Bunnell', 'Mesa', 'Las_Vegas', 'Chattanooga', 'Portland', 'Atlanta', 'Winston-Salem', 'Brownsville', 'Columbia', 'Little_Rock', 'Omaha', 'Lubbock', 'Tampa', 'Unalaska', 'Orlando', 'Salt_Lake_City', 'Columbia', 'Yuma', 'Babbitt', 'Cape_Coral', 'Abilene', 'Palmdale', 'Jackson', 'Greensboro', 'Fresno', 'Shreveport', 'Sacramento', 'Charleston', 'Nightmute', 'Plymouth', 'Milwaukee', 'Arlington', 'Tallahassee', 'Clarksville', 'Durham', 'Palm_Springs', 'Lancaster', 'Knoxville', 'Amarillo', 'Dothan', 'Oak_Ridge', 'Edmond', 'Beaumont', 'Waco', 'Seattle', 'Port_Arthur', 'Baltimore', 'Toledo', 'Kansas_City', 'El_Reno', 'Henderson', 'Jonesboro', 'Ellsworth', 'Caribou', 'Laredo', 'Fort_Wayne', 'North_Las_Vegas', 'Independence', 'Riverside', 'Cincinnati', 'Las_Cruces', 'Cleveland', 'Baton_Rouge', 'Fremont', 'Presque_Isle', 'Des_Moines', 'Lawton', 'Rome', 'North_Port', 'Savannah', 'Lincoln', 'Enid', 'Rio_Rancho', 'Apple_Valley', 'Springfield', 'Victorville', 'Marana', 'Eloy', 'Plano', 'Grand_Prairie', 'Wichita_Falls']


def gen_ip_range():
    first = randrange(1, 256)
    start_ip = ".".join([str(first), str(randrange(1, 256)), str(randrange(1, 256)), str(randrange(1, 256))])
    end_ip = ".".join([".".join(start_ip.split(".")[:-1]), str(randrange(int(start_ip.split(".")[-1:][0]), 256))])
    return start_ip, end_ip


if __name__ == "__main__":
    for x in xrange(100):
        for state, city in zip(states, cities):
            gen_ip_range() 
            start_ip, end_ip = gen_ip_range()
            print start_ip + ',' + end_ip + ',' + state + str(x)
            start_ip, end_ip = gen_ip_range()
            print start_ip + ',' + end_ip + ',' + city + str(x)

