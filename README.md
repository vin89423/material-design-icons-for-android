# Material Design Icons for Android

Provide icon drawable from [.ttf](https://github.com/vin89423/material-design-icons-for-android/tree/master/library/res/raw) font file.
Detect application theme to show light icon or dark icon automatically.
Developer could also assign color and alpha to each drawable. 

## Feature

	// TODO
	
## Screenshot

	// TODO

## Getting Started

	// TODO
	
## How to use

#### Use in ActionBar / toolbar
```java
MaterialIcon md = new MaterialIcon(this);
menu.add("Account Circle").setIcon( materialIcon.draw("ic_account_circle") );

```
#### Use in ImageView
```java
ImageView img = (ImageView) view.findViewById(R.id.imgView);
img.setImageDrawable( MaterialIcon.icon(context, "ic_3d_rotation").isMenuItem(false) );
```

## Icon Licence

Those icons come from Google [Material Design Icons](https://github.com/google/material-design-icons). All icons are released under an [Attribution-ShareAlike 4.0 International](http://creativecommons.org/licenses/by-sa/4.0/) license.

## Library Licence

```
Copyright Vin Wong @ vinexs.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
