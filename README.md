**VPDTest** is a sample Android application developed using **MVVM** architecture, **Room** for local database management, and **Firebase Authentication** for user login. The app simulates account transfers between users, manages transaction histories, and displays user account balances.

This app also uses **RecyclerView** to display a list of user accounts and **DataBinding** to bind UI elements with the ViewModel.

---

## Features

**User Authentication:**
- **Firebase Authentication** to manage login with email and password.

**Account Management:**
- Display a list of user accounts using a **RecyclerView**.
- Each account shows a balance and details.

**Transfer Interface:**
- Users can transfer money between accounts.
- The app validates the transfer amount, ensuring it does not exceed the source account balance.
- After a transfer, users can view a summary of the transfer details before confirming the transaction.

**Transaction History:**
- All transfers are logged in the local **Room** database and displayed in a **RecyclerView**.

---

## Technologies Used

- **Kotlin** – Programming language
- **MVVM Architecture** – To separate concerns and manage UI-related data
- **Room** – For local database management
- **LiveData & ViewModel** – For managing UI-related data lifecycle-aware
- **Firebase Authentication** – To manage user authentication with email/password login
- **RecyclerView** – For displaying lists (accounts and transactions)
- **DataBinding** – To bind data with UI components directly

---

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Android Studio** installed
- **Firebase Project setup** for your app (for Firebase Authentication)
- **Kotlin 1.5** or above
- **Room Database setup**
- Add dependencies in your `build.gradle` files

---

### Firebase Setup:
- Add **Firebase** to your Android project and enable **Firebase Authentication** in your Firebase console.
- Add the necessary configuration in the `google-services.json` file to authenticate users.

### Room Database Setup:
- The **Room database** is used to store and retrieve user accounts and transaction histories locally.

---

## Installation

### Clone the Repository:
Clone this repository to your local machine using Git:

```bash
git clone https://github.com/Ajay519/VPDTestApp.git


## App Structure

### **LoginActivity**:
- Handles user authentication using **Firebase Authentication**.
- Navigates to the main screen upon successful login.

### **MainActivity**:
- Displays a list of user accounts using **RecyclerView**.
- Provides functionality to select source and destination accounts for transfer.

### **TransferActivity**:
- Users can enter transfer details (amount, source, destination account).
- Displays a transfer summary and status after the transaction.

### **AccountViewModel**:
- Handles all data logic (fetching accounts, managing transfers, etc.).
- Uses **Room** for local database operations and **Firebase** for authentication.

### **Room Database**:
- Stores user accounts and transaction history.
- Uses the **AccountDao** and **TransactionDao** interfaces to interact with the database.

## Code Structure

```plaintext
VPDTest/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │   │   ├── example/
│   │   │   │   │   │   ├── vpdtestapp/
│   │   │   │   │   │   │   ├── ui/
│   │   │   │   │   │   │   │   ├── login/
│   │   │   │   │   │   │   │   │   ├── LoginActivity.kt
│   │   │   │   │   │   │   │   ├── main/
│   │   │   │   │   │   │   │   │   ├── MainActivity.kt
│   │   │   │   │   │   │   │   ├── transfer/
│   │   │   │   │   │   │   │   │   ├── TransferActivity.kt
│   │   │   │   │   │   │   ├── data/
│   │   │   │   │   │   │   │   ├── AccountDao.kt
│   │   │   │   │   │   │   │   ├── AccountDatabase.kt
│   │   │   │   │   │   │   │   ├── AccountRepository.kt
│   │   │   │   │   │   │   ├── models/
│   │   │   │   │   │   │   │   ├── UserAccount.kt
│   │   │   │   │   │   │   │   ├── Transaction.kt
│   │   │   │   │   │   │   ├── viewmodel/
│   │   │   │   │   │   │   │   ├── AccountViewModel.kt
│   │   │   │   │   │   │   ├── adapters/
│   │   │   │   │   │   │   │   ├── AccountAdapter.kt
│   │   │   │   │   │   │   ├── binding/
│   │   │   │   │   │   │   │   ├── ActivityMainBinding.kt
│   │   │   │   │   │   │   │   ├── ActivityTransferBinding.kt
│   │   │   │   │   │   │   ├── layouts/
│   │   │   │   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   │   │   │   ├── activity_transfer.xml
│   │   │   │   │   │   │   │   ├── account_item.xml
│   │   │   │   │   │   │   │   ├── item_account.xml
├── build.gradle
├── gradle/
├── settings.gradle
└── README.md

