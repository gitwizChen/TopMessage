A beautiful way of message prompting instead of Toast😈😈😍!

![sample](screenshots/sample.gif)

# Principle
Just set a view to the current activity decor view. May be the effect is different before Android L!

# Install
```
// Gradle
compile 'com.wizchen.topmessage:topmessage:1.0.2'
```

```
// Maven
<dependency>
  <groupId>com.wizchen.topmessage</groupId>
  <artifactId>topmessage</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```

```
// Ivy
<dependency org='com.wizchen.topmessage' name='topmessage' rev='1.0.2'>
  <artifact name='topmessage' ext='pom' />
</dependency>
```

# Usage

```
// the simplest and the default effect
TopMessageManager.showSuccess(activity, msg);
```

```
// use custom duration
TopMessageManager.showSuccess(activity, msg, duration);
```

```
// use custom duration and title
TopMessageManager.showSuccess(activity, msg, duration, title);
```

```
// with a common button
TopMessageManager.showSuccess(activity, msg, title, commonCallback, commonButtonText);
```

```
// with a confitm button and a cancel button
TopMessageManager.showSuccess(activity, msg, title, confirmOrCancelCallback, confirmButtonText, cancelButtonText);
```

```
// with a input area
TopMessageManager.showSuccess(activity, msg, title, sendCallback, sendButtonText, inputHint);
```

Happy to hear any good suggestions! Just stars!

# Todo

- More style and icon
- Show from bottom like SnackBar?

