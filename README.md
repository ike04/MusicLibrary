# MusicLibrary
Shazam API( https://api.rakuten.net/apidojo/api/shazam )を用いた音楽検索アプリ

## 機能
- キーワード検索（APIの仕様上、一度に5件しか取れなかった。。）
- ランキング機能

## 使用技術
- Kotlin
- Clean Architecture (View → ViewModel → Usecase → Repository)
- retrofit
- okhttp
- moshi
- glide
- RxJava3
- groupie
- Hilt

## スクリーンショット
### 検索画面 (GET/search, GET/songs/list-artist-top-tracks)
https://user-images.githubusercontent.com/48178913/123498443-f2019700-d66a-11eb-8adb-8611955a04af.mp4

### ランキング画面 (GET/charts/track)
https://user-images.githubusercontent.com/48178913/123498501-2f662480-d66b-11eb-94b0-8edbbb5a9ed4.mp4

### エラーハンドリング
|  Not match  |  Offline  |
| ---- | ---- |
|  <img src="https://user-images.githubusercontent.com/48178913/123498591-de0a6500-d66b-11eb-9d0d-56063eef9777.gif" >  | <img src="https://user-images.githubusercontent.com/48178913/123498675-6b4db980-d66c-11eb-95c7-962089ef7204.gif">  |
