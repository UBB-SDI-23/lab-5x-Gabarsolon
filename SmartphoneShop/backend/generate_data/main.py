import random
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


generate_displays()