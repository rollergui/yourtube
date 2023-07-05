import 'dart:io';
import 'dart:convert';
import 'package:http/http.dart' as http;

void main(List<String> arguments) {
  exitCode = 0;
  final exp = RegExp(r"v=(\w+)");
  final videoId = exp.firstMatch(arguments[0])?.group(1);
  if (videoId != null) {
    saveVideo(videoId);
  }
}

Future<void> saveVideo(String videoId) async {
  await Directory('output').create();
  final url = Uri.parse(await getVideoUrl(videoId));
  final video = await http.get(url);
  final file = File('output/$videoId.mp4');
  await file.writeAsBytes(video.bodyBytes);
}

Future<String> getVideoUrl(String videoId) async {
  final getInfoUrl = Uri.parse('https://%79%6F%75%74%75%62%65%69%2E%67%6F%6F%67%6C%65%61%70%69%73%2E%63%6F%6D/%79%6F%75%74%75%62%65%69/v1/%70%6C%61%79%65%72?%6B%65%79=%41%49%7A%61%53%79%41%4F%5F%46%4A%32%53%6C%71%55%38%51%34%53%54%45%48%4C%47%43%69%6C%77%5F%59%39%5F%31%31%71%63%57%38');
  final getInfoPayload = '{"context": { "client": { "hl": "en", "clientName": "WEB", "clientVersion": "2.20210721.00.00" } }, "videoId": "$videoId"}';
  final response = await http.post(getInfoUrl, body: getInfoPayload);
  final decoded = json.decode(response.body) as Map<String, dynamic>;
  return decoded['streamingData']['formats'][0]["url"];
}