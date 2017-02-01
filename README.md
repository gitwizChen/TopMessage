A beautiful way of message prompting instead of Toast😈😈😍!

![sample](screenshots/sample.gif)

# Principle
Just set a view to the current activity decor view. May be the effect is different before Android L!

From version 1.0.3, you don't have to set the current activity to manager but the **TopActivityManager** will do this, that's why first step of Usage is so important.
More information please read [this](http://stackoverflow.com/questions/11411395/how-to-get-current-foreground-activity-context-in-android/29786451#29786451).

# Install
```
// Gradle
compile 'com.wizchen.topmessage:topmessage:1.0.4'
```

```
// Maven
<dependency>
  <groupId>com.wizchen.topmessage</groupId>
  <artifactId>topmessage</artifactId>
  <version>1.0.4</version>
  <type>pom</type>
</dependency>
```

```
// Ivy
<dependency org='com.wizchen.topmessage' name='topmessage' rev='1.0.4'>
  <artifact name='topmessage' ext='pom' />
</dependency>
```

# Usage

1. Edit your AndroidManifest.xml like below or extend your CustomApplication from TopApplication.
```
    <application
        android:name="com.wizchen.topmessage.TopApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```

2. Just enjoy yourself!

```
// the simplest and the default effect
TopMessageManager.showSuccess(msg);
```

```
// use custom duration
TopMessageManager.showSuccess(msg, duration);
```

```
// use custom duration and title
TopMessageManager.showSuccess(msg, duration, title);
```

```
// with a common button
TopMessageManager.showSuccess(msg, title, commonCallback, commonButtonText);
```

```
// with a confitm button and a cancel button
TopMessageManager.showSuccess(msg, title, confirmOrCancelCallback, confirmButtonText, cancelButtonText);
```

```
// with a input area
TopMessageManager.showSuccess(msg, title, sendCallback, sendButtonText, inputHint);
```

Happy to hear any good suggestions! Just stars!

# Todo

- More style and icon
- Show from bottom like SnackBar?
- Block current thread so that user can go on after the message view is dismissed?

# License
Copyright 2016 wizChen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.