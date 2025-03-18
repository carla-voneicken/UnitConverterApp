# Unit Converter App
### Video Demo:  https://youtube.com/shorts/eX_xU0tqfas?feature=share

## Description: 
This Android app can be used to convert between different units of length, weight, volume, or temperature.
The app was developed in Android Studio using Kotlin and XML.

## App design:
The app is segmented into four different fragments, one for each kind of conversion (length, weight, volume, 
temperature). Each fragment contains a number of input fields where the user can enter the value they want 
to convert. In real-time, all the converted values are displayed in the corresponding input fields. This way 
there is no need to select the type of unit for the start value and the end value or for a button to start the 
conversion, the conversion happens simultaneously in all fields.
For the volume conversion the user can also choose between the British Imperial System and the U.S. Customary System. 
Both systems use units like Gallon, Pint, and Fluid Ounce, but their volume is slightly different. 
To switch between the different fragments, the user can either swipe or tap the icons in the bottom navigation bar.

## App structure:

### XML layout files:

The layout file for the main activity contains a ViewPager2 (which is the container for the fragments) and
a BottomNavigationView. The items for the bottom navigation are listed separately in menu/bottom_nav.xml.

There are four layout files for the different fragments, which are all very similar. They each contain
a TextView for the title and a specific amount of EditText views (depending on the kind of unit). The EditTexts 
only allow an input of decimal numbers, except for the Temperature fragment, where they also allow signed numbers
to let the user enter negative values (except for Kelvin, which doesn't go below 0).
The Volume fragment layout also contains the radio group for the radio buttons to select between UK and US system.

For each fragment, there is also a version for landscape mode, where the EditTexts are ordered in two columns
instead of one. 

There are also layout files for the colors, themes and strings used in the app.

### Kotlin files:

#### MainActivity:
The MainActivity consists of four sections:
1. Code to bind the views and to handle the fragments with the ViewPager and the BottomNavigationView.
Both need to be synchronized, so when the user changes the fragment by swiping (aka using the ViewPager)
the selector (which highlights the selected fragment) in the BottomNav also needs to be updated and vice versa
when the user changes fragments by tapping the BottomNav, the ViewPager needs to be updated so it knows 
the position of the current fragment.
2. Code to hide the BottomNav when the keyboard is extended. This code calculates the height of the keyboard
and determines if it is larger than 15% of the screen. If so, we can assume that the keyboard is extended and
therefore hide the BottomNav. This code was provided by ChatGPT. 
3. Code to close the keyboard when the user taps outside of EditTexts. This code detects any touch events
and checks, if they happened outside the currently focused EditText, and if so it dismisses the keyboard and 
clears the focus. This code was provided by ChatGPT.
4. An inner class for the FragmentAdapter that the ViewPager uses to get the total amount of fragments and
the fragment at a certain position in the fragments array.

#### Fragments:
Each fragment uses a Kotlin file for the fragment class and an enum class for the ConversionUnit.

The ConversionUnit class contains all the units of that type (e.g. Kilometer, Meter, Mile, ..., for the
Length fragment) with their corresponding toBaseFactors. One unit (e.g. Meter) has a base factor of 1
and the others are corresponding to that (e.g. Kilometer=1000, Centimeter=0.01, ...). The class also contains 
two functions: convertToBase() and convertFromBase(). Using these, the entered value can first be converted
to the base factor and from that base factor all the other unit values can be calculated.

The Fragment classes all follow a common structure: 
1. A map of the EditTexts and their associated conversion units from the enum class is declared. This map 
is frequently used to loop over all EditTexts.
2. The setupFocusChangeListener() function sets FocusChangeListeners on all EditTexts that detect if
the focus of an EditText changes (e.g. the user selects a different EditText than before) and, if so, clears 
all fields.
3. The setupAllTextWatchers() function calls the setupTextWatcher() function for all EditTexts in the map.
4. The setupTextWatcher() function is where the user input is detected and processed in real-time: 
   - It first checks if the global ignoreTextChanges variable is true to prevent an infinite loop by triggering 
   text change events when changing the values of the other EditTexts.
   - It then checks if the EditText is empty or null (e.g. if the user deletes all characters), and in 
   that case clears all the fields.
   - Using a try-catch-finally block, it then sets the ignoreTextChanges to true, then checks if the input
   ends with a '.' which would mean the user is probably trying to enter a decimal number and not finished,
   so in that case the function just returns. If this is not the case, the input is converted to a double
   (or if that's not possible to null). Only when the input is not null, the updateFields() function gets
   called to update all the other EditTexts.
   - If this throws an exception (which is not expected, because the EditTexts only allow the input of decimal
   numbers anyways), the user is alerted via a Toast.
   - Finally after the whole block was run the ignoreTextChanges variable is set back to false.
5. The updateFields() function (which takes the user input and the type of unit as parameters) calculates
the base value (using the ConversionUnit class mentioned above) and then loops over all the EditTexts and displays
the corresponding value (using the convertFromBase() function).
6. The clearAllFields() function loops over all EditTexts and sets their text to "".

The Volume fragment is also based on that structure, but is a bit more complex because it has to take
into account the system (UK or US) the user selected. Therefore it uses two maps of EditTexts and their
corresponding conversion units. In the updateFields() function it has to first determine which radio button
was selected and then select the map with the correct conversion units.

For the Temperature fragment there is no ConversionUnit class, because the conversion rules are too complicated
and non-linear. Instead there are six different functions within the fragment containing the rules for 
converting between Celsius, Fahrenheit and Kelvin. The updateFields() function calls these to calculate and
display the correct values.

## How I approached this project and challenges I faced:

After taking a beginner's course on Android Development, which included a tutorial on a very basic version
of this app (only allowing conversion between centimeter and inches), I got the idea for this project.
My main vision was a unit converter that shows all values in real-time, because I always find it annoying
to first select the start and target units.

Because I didn't know how to approach the real-time display, I worked with ChatGPT and Gemini to learn 
about TextWatchers and TextChangedListeners. I also consulted the AI for many small questions and issues, 
having it explain every bit of code in detail to make sure I understand how it works. I also learned to question
the validity of the code provided by the AI, as I found quite often the code overly complicated and sometimes
outright wrong.

After making the first fragment work, I used the structure to build the other fragments, tweaking and 
improving it a great deal along the way. Only very far into the process I implemented the maps for the 
EditTexts which allowed me to loop over them, making my code significantly shorter and more readable.

I also did a lot of testing, making sure the output of the app is actually correct. During this I discovered 
and fixed a few bugs I had introduced (e.g. not allowing decimals at first or allowing Kelvin to be negative).

I also discovered one bug, that even after extensive search online and trying many AI suggestions I could not fix:
Two of the four fragments (weight and volume) show this bug: When the user selects them by tapping the bottom 
nav (not by swiping) and then selects an EditText, the keyboard extends but the EditText loses focus, so it has to 
be selected again before being able to enter a number. This only happens right after changing the fragment,
not when the keyboard in that fragment was already opened and closed and is opened again. It seems to 
be a focus-related issue, but I could not figure out why it only happens in two fragments, because all 
of them have the same basic structure. After spending many frustrating hours on it, I decide to keep the 
bug for now, because it is only a very minor bug and doesn't limit the functionality of the app.

## Further enhancements:
- Fixing that bug mentioned above.
- The option to convert typical US and UK sizes that consist of two units (e.g. the height of a person 
in foot and inch) into the corresponding metric unit (e.g. meter).
- Offering different settings:
  - For the decimal format (aka how many places after the decimal point)
  - For the language
  - If '.' or ',' is used for the decimal point
  - Option to select or deselect certain units to only show the fields the user actually needs
