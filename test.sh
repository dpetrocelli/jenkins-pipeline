release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
if [[ $release = production-* ]] ; then
    echo $release
else
    echo "not a valid commit"
fi

