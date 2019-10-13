#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
using namespace cv;
using namespace std;
 
int main()
{
    cout <<"BEGIN";
    Mat image = imread("Tvar.jpg");
    Mat grey = Mat::zeros(image.rows, image.cols, CV_8UC1);
    for (int i = 0; i < image.rows; i++)
    {
 
        for (int j = 0; j < image.cols; j++)
        {
            int blue = image.at<Vec3b>(i, j)[0];
            int green = image.at<Vec3b>(i, j)[1];
            int red = image.at<Vec3b>(i, j)[2];
 
            grey.at<uchar>(i, j) = (uchar)(0.114*blue + 0.587*green + 0.299*red);
        }
    }
    imwrite("image_bw.jpg", grey);
    cout <<"END";
}