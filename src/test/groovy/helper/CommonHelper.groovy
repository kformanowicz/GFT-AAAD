package helper

import groovy.json.JsonSlurper


class CommonHelper {

    static String jsonToString(String jsonLocation){
        Scanner input = new Scanner(new File(jsonLocation))
        StringBuilder jsonObject = new StringBuilder()
        while(input.hasNextLine()){
            jsonObject.append(input.nextLine())
        }
        return jsonObject.toString()
    }

    static def jsonToObject(String jsonLocation){
        String json = jsonToString(jsonLocation)
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(json)
        return object
    }
}
