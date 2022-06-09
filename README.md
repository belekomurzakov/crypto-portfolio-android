# Crypto Portfolio (Android)
Crypto portfolio mobile app built for my EBC-VA1 - Application Development for Android course.

## What I Learned
- Designed in MVVM architecture using Dependency Injection
- Implemented custom chart using **MPAndroidChart** external library
- Persistence of data to SQLite database (Room library)

## Main Features
<p align="center"><img src="app/src/main/res/drawable/play.gif" width="220"/></p>

## Screenshots
<p align="center">
<img src="app/src/main/res/drawable/analytics.png" width="220"><img src="app/src/main/res/drawable/prices.png" width="220" /><img src="app/src/main/res/drawable/add_crypto.png" width="220" /><br>
<img src="app/src/main/res/drawable/portfolio.png" width="220" /><img src="app/src/main/res/drawable/history.png" width="220" /><img src="app/src/main/res/drawable/remove_crypto.png" width="220" />
</p>

## Repo Structure
```
/
├─ app/src/main/
│  ├─java/com/omurzakov/cryptoportfolio/
│  │                    ├─ activities/    # Activity classes
│  │                    ├─ architecture/  # BaseFragment
│  │                    ├─ database/      # Database configuration, Repository layer
│  │                    ├─ di/            # Dependency Injection
│  │                    ├─ fragments/     # Fragment classes
│  │                    ├─ model/         # Database models
│  │                    ├─ utilities/     # 
│  │                    ├─ viewmodels/    # 
│  │                    └─ views/         # Custom views
│  │
│  └─res/                                 # XML files for view
│ 
└─ README.md                              # This file
```
