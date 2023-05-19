(ns yourtube
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json])
  (:require [clojure.java.io :as io]))

(def get-info-url "https://%79%6F%75%74%75%62%65%69%2E%67%6F%6F%67%6C%65%61%70%69%73%2E%63%6F%6D/%79%6F%75%74%75%62%65%69/v1/%70%6C%61%79%65%72?%6B%65%79=%41%49%7A%61%53%79%41%4F%5F%46%4A%32%53%6C%71%55%38%51%34%53%54%45%48%4C%47%43%69%6C%77%5F%59%39%5F%31%31%71%63%57%38")

(def get-info-payload "{\"context\": { \"client\": { \"hl\": \"en\", \"clientName\": \"WEB\", \"clientVersion\": \"2.20210721.00.00\" } }, \"videoId\": \"")

(defn get-video-info
  [video-id]
  (:body (client/post get-info-url {:content-type :json
                                    :body (str get-info-payload video-id "\" }")})))

(defn fetch-file
  "Fetches file over http"
  [url]
  (let [res (client/get url {:as :byte-array})]
    (if (= (:status res) 200)
      (:body res))))

(defn get-video-url
  [video-id]
  (str (get (first (get-in (json/read-str (get-video-info video-id)) ["streamingData" "formats"])) "url")))

(defn save-video
  "downloads and stores the video on disk"
  [video-id]
  (let [p (fetch-file (get-video-url video-id))]
    (if (not (nil? p))
      (with-open [w (io/output-stream (str video-id ".mp4"))]
        (.write w p)))))

(defn -main
  "Entrypoint"
  [arg]
  (prn "Starting download")
  (save-video (last (re-find #"v=(\w+)" arg)))
  (prn "Download complete"))
