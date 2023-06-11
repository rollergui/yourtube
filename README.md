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

OR if you have docker installed, that's an option as well. build the image and run it as:
```sh
docker run -v $(pwd)/output:/app/output image-name "video-url"
```

## feature plan:

- options to choose:
  - video quality
  - audio only
  - output dir/name
- download from other video platforms
- improved interface (still CLI)
