Process:
- Started with determining requirements of project
- Decided on external libraries needed
    - only needed android platform libraries
    - could have used ViewModels and coroutines but seemed exessive for this project
- Created data classes
- Created initial list view implementation
- Tested list view implementation
- Created dialog and immplemented remaining functionality
- Iterated on design based on new understanding of requirements
    - switched to parsing JSON instead of justing creating POKOs

Downloading and running:
1. Download LoadBoard.zip
2. Extract the files from the zip folder
3. Download and install Android Studio
    - https://developer.android.com/studio/install
4. Click the open existing project option once Android Studio has started
5. Navigate to the root directory of the project and select the LoadBoard folder and click ok
6. Android Studio will now try to sync dependencies and get everything set up
7. If a dialog pops up saying no Android SDK is installed do the following:
    a. Open the Tools menu
    b. Click the SDK Manager
    c. At the top it will indicate the location of the Android SDK
    d. Click the Edit button near that path
    e. Go through the wizard to install the SDK
    f. Stay in the SDK Manager
    g. In the SDK Platforms tab, select Android 9.0 (Pie)
    h. Switch to the SDK Tools tab
    i. Select Android SDK Platform-Tools and Android SDK Tools
    j. Check the "Show Package Details" checkbox in the bottom right corner
    k. Select version "28.0.3" for the Android SDK Build Tools
    l. Click apply and go through the installation process
7. If you do not have an emulator installed do the following:
    a. Open the Tools menu
    b. Click the AVD Manager
    c. Click the Create Virtual Device button
    d. Select a phone option and click next
    e. Select "Q"
        - You may need to download it via the Download button next to the release name
    f. Click next
    g. Click next again
    h. Click finish
8. In the main window of Android Studio, find the green triangle button in the top toolbar and click it to start an emulator and run the app
