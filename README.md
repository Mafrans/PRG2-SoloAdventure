# Post mortem
### Inledning
Syftet med arbetet är att skapa en redigerare till ett spel som kan spelas av 
en spelare. Spelet ska sparas i en databas och hela CRUD ska inkluderas. 
Arbetet skrevs med objektorienterad design i åtanke och utformades för att 
vara modulärt och lättutökat i framtiden, detta uppnåddes genom ett MVC-tänk 
samt separering av logik till sina egna komponenter.

### Bakgrund
Projektet använder en blandning av Kotlin och Java, två robusta och 
objektorienterade språk som är mycket populära inom multiplattforms-programmering. 

För att renderar text i konsollen på ett smidigare sätt JAnsi, ett bibliotek 
som tillåter rendering av färgad text i konsollen samt bakgrunder på texten. 

För att rendrera redigerarens GUI används Swing, ett bibliotek som är 
inbyggt i Java och bygger upp GUI-vyer på ett naturligt och smidigt sätt.

För att lättare hantera databasen används Morphia, ett databas-bibliotek 
som automatiskt hanterar databasreferenser och bygger uppdatabaskollektioner 
i MongoDB. Morphia hjälpte även med att lösa problemet med MVC, genom att ha 
en separat modell som hanteras av Morphia blev det lätt att inkludera den 
i en controller och sedan koppla det till view-delen som hanteras av Swing.

### Positiva erfarenheter
Projektet var en utmaning inom strukturerad datauppbyggnad, och idén med flera 
olika GUI-mallar som kopplas ihop gick mycket bra. Jag tyckte även det gick 
bra med skapandet av special-byggda Swing-komponenter för mer komplicerad logik. 
Swing var lite jobbigt att hantera i början, men efterhand blev det lättare och 
lättare att förstå hur det fungerar.

### Negativa erfarenheter
Jag blev inte klar med arbetet, och det saknas fortfarande en spelbar klient. 
En del av databas-strukturen är också klumpig och ineffektiv.

### Sammanfattning
Projektet blev större än väntat och tog betydligt mycket längre tid än jag hade 
planerat. Jag tror att resultatet skulle blivit betydligt bättre om jag hade mer 
tid och ork att arbeta på det, men efter den mängd timmar som spenderats blev det 
helt enkelt för mycket arbete. Jag har dock lärt mig mycket och haft roligt, speciellt 
har jag lärt mig om Swing och Morphia, som båda är bibliotek jag kommer använda i 
framtiden.