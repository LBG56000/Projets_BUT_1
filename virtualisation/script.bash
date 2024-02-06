#!/usr/bin/bash
docker volume create sae103
version=$(cat config | egrep "VERSION" | cut -d "=" -f 2)
docker container run -d --name sae103-forever -v sae103:/work bigpapoo/clock

for boucle in *.c
do
        docker container cp $boucle sae103-forever:/work
done

docker container cp doc-user.md sae103-forever:/work
docker container cp config sae103-forever:/work
docker container cp gendoc-tech.php sae103-forever:/work
docker container cp gendoc-user.php sae103-forever:/work

docker container exec sae103-forever ls -la /work

#gen doc user
docker container run --rm -v sae103:/work bigpapoo/sae103-php sh -c php -f gendoc-tech.php > doc-tech-$version.html
docker container run --rm -v sae103:/work bigpapoo/sae103-php php gendoc-user.php > doc-user-$version.html
docker container cp doc-tech-$version.html sae103-forever:/work
docker container cp doc-user-$version.html sae103-forever:/work
docker container run --rm -v sae103:/work bigpapoo/sae103-html2pdf 'html2pdf doc-tech-'$version'.html doc-tech-'$version'.pdf'
docker container run --rm -v sae103:/work bigpapoo/sae103-html2pdf 'html2pdf doc-user-'$version'.html doc-user-'$version'.pdf'


docker exec sae103-forever mkdir dossier_sae103
for boucleC in *.c
do
        docker exec sae103-forever mv /work/$boucleC /work/dossier_sae103
done
for bouclePDF in doc-user-$version.pdf doc-tech-$version.pdf
do
        docker exec sae103-forever mv work/$bouclePDF work/dossier_sae103
done
for boucleHTML in *.html
do
        docker exec sae103-forever mv /work/$boucleHTML /work/dossier_sae103
done

docker container run --rm -v sae103:/work bigpapoo/sae103-php sh -c "tar czvf dossier_sae103.tar.gz dossier_sae103"
docker container run --rm -v sae103:/work bigpapoo/sae103-php sh -c "ls -la"
docker cp sae103-forever:/work/dossier_sae103.tar.gz .

docker kill sae103-forever
docker container prune -f
docker volume rm sae103
