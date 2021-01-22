# Profile MVVM RealmDB Example
Example code for using Realm work with MVVM Method


![ezgif com-gif-maker](https://user-images.githubusercontent.com/77658913/105454840-88491f80-5cbd-11eb-9458-fffc3f60da78.gif)

## Library Used
- [RealmDB](https://realm.io/docs/kotlin/latest/) :speech_balloon:
- [Glide](https://github.com/bumptech/glide) :speech_balloon:

## RealmDB Module
``` kotlin
open class Person(
    var name: String = "",
    var title: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var dob: String = "",
    var gender: String = "",
    var description: String = ""
) : RealmObject()
```

## Person Repo
To control all RealmDB function for Person Module
``` kotlin
class PersonRepo {
    private val realm by lazy { Realm.getDefaultInstance() }

    private val _personData = MutableLiveData<Person?>()
    val getPersonData: LiveData<Person?> get() = _personData

    private val _updateSuccess = MutableLiveData<Boolean>()
    val getUpdateSuccess: LiveData<Boolean> get() = _updateSuccess

    fun loadPersonDetail() {
        val personData = realm.where(Person::class.java).findFirst()
        if (personData != null)
            _personData.value = realm.where(Person::class.java).findFirst()
        else
            addDefaultData()
    }

    fun updatePersonDetail(newData: Person) {
        realm.executeTransactionAsync(
            Realm.Transaction {
                val person = it.where(Person::class.java).findFirst()
                person?.name = newData.name
                person?.title = newData.title
                person?.email = newData.email
                person?.phoneNumber = newData.phoneNumber
                person?.description = newData.description
            }, Realm.Transaction.OnSuccess {
                // Original queries and Realm objects are automatically updated.
                _updateSuccess.value = true
            })
    }

    private fun addDefaultData() {
        realm.executeTransactionAsync({
            val newItem = Person().apply {
                name = "Low Wei Siong"
                title = "Senior Software Engineer"
                email = "wsiong083@gmail.com"
                phoneNumber = "+60163346331"
                description =
                    "Senior Android developer. Good knowledge of mobile platform architecture. Experience to work and discuss with iOS team, Backend team and Design Team in AGILE(JIRA and Confluent). Experience to leading a team to develop a new project, manage the timeline and monitor code quality. Keep update and share newest coding skill"
            }
            it.insert(newItem)

        }, Realm.Transaction.OnSuccess {
            // Original queries and Realm objects are automatically updated.
            loadPersonDetail()
        })
    }
}
```

## Personal Edit View Model
Using this view model to store and do some action
``` kotlin
class PersonalEditViewModel : BaseViewModel() {

    private val personRepo by lazy { PersonRepo() }
    val personData = personRepo.getPersonData
    val personUpdateSuccess = personRepo.getUpdateSuccess

    @Bindable
    val nameEditText = MutableLiveData<String>()

    @Bindable
    val titleEditText = MutableLiveData<String>()

    @Bindable
    val emailEditText = MutableLiveData<String>()

    @Bindable
    val phoneEditText = MutableLiveData<String>()

    @Bindable
    val aboutEditText = MutableLiveData<String>()

    init {
        loadPersonData()
    }

    fun setupAllDetail(person: Person?) {
        if (person != null) {
            nameEditText.value = person.name
            titleEditText.value = person.title
            emailEditText.value = person.email
            phoneEditText.value = person.phoneNumber
            aboutEditText.value = person.description
        }
    }

    fun clickEdit() {
        val person = Person().apply {
            name = nameEditText.value.toString()
            title = titleEditText.value.toString()
            email = emailEditText.value.toString()
            phoneNumber = phoneEditText.value.toString()
            description = aboutEditText.value.toString()
        }

        personRepo.updatePersonDetail(person)
    }

    private fun loadPersonData() {
        personRepo.loadPersonDetail()
    }
}
```

## Edit profile layout
Auto refresh the view when the retreived the data
``` xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <import type="com.lwseasy.lwsprofile.constant.ClickModel" />

        <variable
            name="viewModel"
            type="com.lwseasy.lwsprofile.view.personal_edit.PersonalEditViewModel" />
    </data>

    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fillViewport="true"
        android:overScrollMode="never">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:padding="@dimen/global_16dp">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/global_24dp"
                android:onClick="@{()->viewModel.clickButton(ClickModel.Back)}"
                android:src="@drawable/ic_back_round_default" />

            <TextView
                style="@style/fontNunito700"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginVertical="30dp"
                android:layout_marginEnd="@dimen/global_24dp"
                android:text="Edit Personal Detail"
                android:textAllCaps="true"
                android:textColor="@color/black_2C2323"
                android:textSize="@dimen/text_42sp" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/global_8dp"
                android:layout_marginBottom="@dimen/global_16dp"
                android:background="@drawable/button_radius_transparent_default"
                android:gravity="center_vertical"
                android:padding="@dimen/global_16dp">

                <EditText
                    style="@style/fontNunito400"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:hint="Name"
                    android:singleLine="true"
                    android:text="@={viewModel.nameEditText}"
                    android:textColor="@color/black_2C2323"
                    android:textSize="@dimen/text_15sp" />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/global_8dp"
                android:layout_marginBottom="@dimen/global_16dp"
                android:background="@drawable/button_radius_transparent_default"
                android:gravity="center_vertical"
                android:padding="@dimen/global_16dp">

                <EditText
                    style="@style/fontNunito400"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:hint="Title"
                    android:singleLine="true"
                    android:text="@={viewModel.titleEditText}"
                    android:textColor="@color/black_2C2323"
                    android:textSize="@dimen/text_15sp" />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/global_8dp"
                android:layout_marginBottom="@dimen/global_16dp"
                android:background="@drawable/button_radius_transparent_default"
                android:gravity="center_vertical"
                android:padding="@dimen/global_16dp">

                <EditText
                    style="@style/fontNunito400"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:hint="Email"
                    android:inputType="textEmailAddress"
                    android:singleLine="true"
                    android:text="@={viewModel.emailEditText}"
                    android:textColor="@color/black_2C2323"
                    android:textSize="@dimen/text_15sp" />

            </LinearLayout>


            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="@dimen/global_16dp"
                android:background="@drawable/button_radius_transparent_default"
                android:gravity="center_vertical"
                android:padding="@dimen/global_16dp">

                <EditText
                    style="@style/fontNunito400"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:hint="Phone Number"
                    android:inputType="number"
                    android:text="@={viewModel.phoneEditText}"
                    android:textColor="@color/black_2C2323"
                    android:textSize="@dimen/text_15sp" />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="@dimen/image_200dp"
                android:layout_marginBottom="@dimen/global_16dp"
                android:background="@drawable/button_radius_transparent_default"
                android:padding="@dimen/global_16dp">

                <EditText
                    style="@style/fontNunito400"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:hint="About Me"
                    android:inputType="textMultiLine"
                    android:text="@={viewModel.aboutEditText}"
                    android:textColor="@color/black_2C2323"
                    android:textSize="@dimen/text_15sp" />

            </LinearLayout>


            <TextView
                style="@style/fontNunito600"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginEnd="@dimen/global_8dp"
                android:background="@drawable/button_radius_default"
                android:onClick="@{()->viewModel.clickEdit()}"
                android:padding="@dimen/global_16dp"
                android:text="Edit"
                android:textAlignment="center"
                android:textColor="@color/white" />

        </LinearLayout>
    </androidx.core.widget.NestedScrollView>
</layout>
```
