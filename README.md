# CHAMPtitles-Coding-Challenge

# Note : Please give the location of your text file in line number 19.

# Explanation

I read the given file into a Stream. While iterating the stream, first I split the text into sentences. 

Then I iterate each sentence and split the text into words, I used HashMap to store the words and their frequencies as keys and values respectively(as inserting, retrieving and computing is faster). 

As I've already stored frequencies, I've computed the maximum frequency of each word to find out the last sentence of the most used word instead of finding it separately.

To find the total word count in the text file, I am looping through the HashMap values as it contains the count of each word.

To find the top 10 words, I took the entries of HashMap into a list to sort them. I've used lamda to sort based on their frequencies.

If the frequencies are same then I sorted lexicographically to display.
