from flask import Flask,request,jsonify
import numpy as np
import pickle

model = pickle.load(open('model.pkl','rb'))


dict = {
    "Herman Miller" : 5,
    "Humanscale": 10,
    "Haworth": 15,
    "Marie Claire":20,
    "Ficmax": 25,
    "terrific office":30
}


dict2 = {
    "orange" : 35,
    "blue": 5,
    "tan": 10,
    "gray":15,
    "black": 20,
    "carbon": 25,
    "red":30
}

app = Flask(__name__)
@app.route('/')
def index():
    return "Hello world"
@app.route('/predict',methods=['POST'])
def predict():
    brand = dict[request.form.get('brand')]
    age = request.form.get('age')
    color = dict2[request.form.get('color')]
    height = request.form.get('height')
    width = request.form.get('width')
    depth = request.form.get('depth')
    input_query = np.array([[brand,age,color, height, width, depth]])
    result = model.predict(input_query)[0]
    return jsonify({'result':str(result)})


if __name__ == '__main__':
    print("flask running")
    app.run(debug=True, host="0.0.0.0")
