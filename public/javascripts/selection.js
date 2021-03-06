
function select(name) {
    document.getElementById('start-game').disabled=false;
    document.getElementById('Bob').style.border = "none";
    document.getElementById('Alice').style.border = "none";
    document.getElementById('Nova').style.border = "none";
    document.getElementById('HomTanks').style.border = "none";
    document.getElementById('BerryStraw').style.border = "none";
    document.getElementById('CaptainSpeckJarrow').style.border = "none";

    switch (name) {
        case "Bob":
            document.getElementById('description').value='Bob (20 Years) is an average business administration student from Germany. His grades are like the average and he has his moments. Sometimes he can be quite pessimistic. He was flying to a student exchange when the plane went down in a heavy storm and he reached the beach of an island.';
            document.getElementById('Bob').style.border = "2px solid red";
            break;
        case "Alice":
            document.getElementById('description').value='A strong and beautiful woman, who is the captain of a very dangerous pirate crew. During a heavy storm her ship sunk and she had lost her crew but not her faithful pet CuCu. Together they reached the beach of an island.';
            document.getElementById('Alice').style.border = "2px solid red";
            break;
        case "Nova":
            document.getElementById('description').value='Special agent trained for secret missions. Her missions are so secret that there are no more details available. She is a professional, fast and athletic, but has her difficulties in dealing with people in social interactions. In a heavy storm her plane crashed into the ocean, but Nova managed to escape and reach the beach of an island.';
            document.getElementById('Nova').style.border = "2px solid red";
            break;
        case "HomTanks":
            document.getElementById('description').value='Hom Tanks leads a division of the parcel delivery service "Ted Ex" and is considered to be the perfect employee. He is sure of himself, efficient and identifies himself a lot with the "Ted Ex" company. On a business trip his plane went down, all the packets got lost, and he found himself at the beach of an island.';
            document.getElementById('HomTanks').style.border = "2px solid red";
            break;
        case "BerryStraw":
            document.getElementById('description').value='A schoolgirl with the age of 12, switching very quickly between positive and negative emotions. Some would call her a "crybaby", others a sunshine. She went on vacation by plane with her parents when the plane had to land on water. Afterwards, everyone survived and got rescued, just Berry was forgotten and after spending some tears she reached the beach of an island.';
            document.getElementById('BerryStraw').style.border = "2px solid red";
            break;
        case "CaptainSpeckJarrow":
            document.getElementById('description').value='Captain Speck Jarrow is a captain stationed at Port Paradise in the caribbean sea and the whole pride of the british royal navy. He is a funny, good-natured person who\'s strategy in ship navigation is based on the goal to stay as near to harbors as possible to avoid any shortage of taverns and his beloved rum. In a heavy storm his ship sank (because it was overcharged with rum barrels) and he reached the beach of an island.';
            document.getElementById('CaptainSpeckJarrow').style.border = "2px solid red";
            break;
    }
}

// creating a new game by giving the character name
function createNewGame()
{
    var result;
    var splitResult="";

    var charSelected = "";

    // Added new functionality to find out which character was selected

    if(document.getElementById('Bob').style.border === "2px solid red")
    {
        charSelected="Bob";
    }
    else if(document.getElementById('Alice').style.border === "2px solid red")
    {
        charSelected="Alice";
    }
    else if(document.getElementById('Nova').style.border === "2px solid red")
    {
        charSelected="Nova";
    }
    else if(document.getElementById('HomTanks').style.border === "2px solid red")
    {
        charSelected="HomTanks";
    }
    else if(document.getElementById('BerryStraw').style.border === "2px solid red")
    {
        charSelected="BerryStraw";
    }
    else if(document.getElementById('CaptainSpeckJarrow').style.border === "2px solid red")
    {
        charSelected="CaptainSpeckJarrow";
    }

    charSelected += "-";

    model_data = JSON.stringify(charSelected);

    // call of java method without parameter
    $.ajax({
        url: '/createGame/'+charSelected,
        type: 'POST',
        contentType: 'application/json',
        data: model_data,
        dataType: 'json html',
        converters: {
            'text json': true
        },
        success: function (response) {
            response = JSON.parse(response);
            result = response;


            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    // result would be "successful" as string, but will not be used here

                    // prepare for the next splitresult to read; in the basic version of this method there should be none
                    result=result.substring(i+1);
                    i=0;
                }
            }
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });
}

