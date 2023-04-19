import random

import faker.providers.company
from faker import Faker

fake = Faker()

def generate_displays():
    type = ["Super AMOLED","LTPO Super Retina XDR OLED","Monochrome graphic","Fluid AMOLED","Super Retina OLED"
        "AMOLED","LTPO AMOLED","IPS LCD","TFT" ]
    protection = [ "Asahi Dragontrail Glass", "Ceramic Shield Glass", "PVC", "Corning Gorilla Glass Victus",
        "Corning Gorilla Glass 4", "Scratch-ressistance glass"]

    with open('displays.sql', 'w') as f:
        for i in range(1000):
            f.writelines("INSERT INTO display(type, size, resolution_width, resolution_height, protection) VALUES ")
            for j in range(999):
                f.writelines("(" +
                             "'" + random.choice(type) + "'" "," +
                             str("%.2f" % random.uniform(4, 6.7)) + "," +
                             str(random.randint(800, 3840)) + "," +
                             str(random.randint(800, 3840)) + "," +
                             "'" + random.choice(protection) + "'" + "), "
                             )
            f.writelines("( " +
                         "'" + random.choice(type) + "'" "," +
                         str("%.2f" % random.uniform(4, 6.7)) + "," +
                         str(random.randint(800, 3840)) + "," +
                         str(random.randint(800, 3840)) + "," +
                         "'" + random.choice(protection) + "'" + ");\n"
                         )
def generate_smartphones():
    brands_dict = {
        'Apple': ['iPhone 13 Pro', 'iPhone 12', 'iPhone SE'],
        'Samsung': ['Galaxy S21', 'Galaxy Note 20', 'Galaxy A52'],
        'Huawei': ['Mate 40 Pro', 'P40 Pro', 'Nova 8'],
        'Xiaomi': ['Mi 11', 'Redmi Note 10 Pro', 'Poco X3 Pro'],
        'OPPO': ['Find X3 Pro', 'Reno6 Pro', 'A94'],
        'Vivo': ['X60 Pro', 'Y72 5G', 'V21 5G'],
        'OnePlus': ['9 Pro', 'Nord 2', '8T'],
        'Google': ['Pixel 5', 'Pixel 4a', 'Pixel 3a'],
        'Sony': ['Xperia 1 III', 'Xperia 5 II', 'Xperia 10 II'],
        'LG': ['Wing', 'Velvet', 'G8 ThinQ'],
        'HTC': ['U12+', 'Desire 21 Pro 5G', 'Wildfire E2'],
        'Motorola': ['Edge 20 Pro', 'Moto G Power', 'Moto E7 Power'],
        'Nokia': ['G10', '5.4', '3.4'],
        'Asus': ['ZenFone 8', 'ROG Phone 5', 'ZenFone 7 Pro'],
        'Lenovo': ['Legion Phone Duel 2', 'K13 Note', 'Tab P11 Pro'],
        'ZTE': ['Axon 30 Ultra', 'Blade V30', 'Axon 11 SE'],
        'Realme': ['GT Neo 2', 'Narzo 30 Pro 5G', 'C21Y'],
        'Tecno': ['Camon 17 Pro', 'Pova 2', 'Spark 7'],
        'Infinix': ['Hot 10T', 'Note 10 Pro', 'Zero 8'],
        'BlackBerry': ['KEY2', 'Motion', 'Evolve X']
    }

    with open('smartphones.sql', 'w') as f:
        for i in range(1000):
            f.writelines("INSERT INTO smartphone()")


generate_displays()