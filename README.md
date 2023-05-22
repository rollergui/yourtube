# yourtube

## running:

```sh
clj -M:download "youtube-url-here"
```
Video will be downloaded to project folder as `someVideoId.mp4`

---

OR if you have java installed, pick up the .jar release and run it as
```sh
java -jar yourtube.jar "https://www.youtube.com/watch?v=3QFhxUyMyM4"
```
video file will be downloaded to current directory

## feature plan:

- containerized application
- options to choose:
  - video quality
  - audio only
  - output dir/name
- download from other video platforms
- improved interface (still CLI)
