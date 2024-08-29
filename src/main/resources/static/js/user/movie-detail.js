function convertToEmbedCode(youtubeUrl) {
    let videoId = youtubeUrl.split('v=')[1];
    const ampersandPosition = videoId.indexOf('&');
    if (ampersandPosition !== -1) {
        videoId = videoId.substring(0, ampersandPosition);
    }
    const embedUrl = `https://www.youtube.com/embed/${videoId}`;
    return `<iframe width="600" height="315" src="${embedUrl}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;
}

// Example usage
const youtubeUrl = document.getElementById('videoContainer').innerText;
const embedCode = convertToEmbedCode(youtubeUrl);

// Replace the URL with the embed code
document.getElementById('videoContainer').innerHTML = embedCode;