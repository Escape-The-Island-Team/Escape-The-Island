
function select(name) {
    document.getElementById('start-game').disabled=false;

    switch (name) {
        case "Bob":
            document.getElementById('description').value='Bob (20 Years) is a normal business administration student from Germany. His grades are like the average. He likes to eat bananas and hates coconuts. He is not very strong but he has his moments and becomes very smart. Some years ago he had a girlfriend, but not for long. Because he is very pessimistic and heartless.';
            document.getElementById('Bob').style.border = "2px solid red";
            break;

        case "Alice":
            document.getElementById('description').value='A strong and beautiful woman, who is the captain of a very dangerous pirate crew. After a heavy storm has passed, her ship was damaged and she had lost her crew but not her faithful pet CuCu.';
            document.getElementById('Alice').style.border = "2px solid red";
            break;
    }

}