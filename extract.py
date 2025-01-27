import os
import json
import shutil

har_file_path = input("请输入HAR文件的路径: ")


json_output_directory = 'json_files'
output_file = 'sinner.txt'

if not os.path.exists(json_output_directory):
    os.makedirs(json_output_directory)

def extract_rest_ids(data, rest_ids):
    if isinstance(data, dict):
        for key, value in data.items():
            if key == 'rest_id':
                rest_ids.append(value)
            else:
                extract_rest_ids(value, rest_ids)
    elif isinstance(data, list):
        for item in data:
            extract_rest_ids(item, rest_ids)


with open(har_file_path, 'r', encoding='utf-8') as har_file:
    har_data = json.load(har_file)


for entry in har_data['log']['entries']:
    request = entry['request']
    response = entry['response']
    request_url = request['url']
    response_status = response['status']
    if request['method'] == 'GET' and 'SearchTimeline' in request_url and response_status == 200:

        json_filename = os.path.join(json_output_directory, f"{entry['startedDateTime']}.json")
        response_body = response['content']['text']
        with open(json_filename, 'w', encoding='utf-8') as json_file:
            json.dump(json.loads(response_body), json_file)


with open(output_file, 'w') as outfile:

    for filename in os.listdir(json_output_directory):
        if filename.endswith('.json'):
            filepath = os.path.join(json_output_directory, filename)

            with open(filepath, 'r', encoding='utf-8') as jsonfile:
                data = json.load(jsonfile)
                rest_ids = []
                extract_rest_ids(data, rest_ids)
                for rest_id in rest_ids:
                    outfile.write(f"{rest_id}\n")
shutil.rmtree(json_output_directory)